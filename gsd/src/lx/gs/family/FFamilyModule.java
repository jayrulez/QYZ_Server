package lx.gs.family;


import cfg.*;
import cfg.mall.BlackMall;
import common.Time;
import common.Utils;
import gnet.link.Onlines;
import lx.gs.family.msg.SBlackMallList;
import lx.gs.map.FMap;
import map.msg.XResetRoleParty;
import xdb.Procedure;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public enum FFamilyModule implements gs.Module, gs.RoleCreateListener,  gs.RoleLoginListener, gs.RoleDayOverListener, gs.DayOverListener{

	INSTANCE;

	public final static long ACTIVITY_TIME_DETA = 10*60*1000;
	public final static long MAX_OFFLINE_DAY = 10;
    public final static int FAMILY_MAX_APPLAY_NUM = 5;
    public final static int FAMILY_MAX_REQUEST_SIZE = 10;
    public final static int NORMAL_FAMILY_APPLY_NUM = 3;
    public final static int SYSTEM_FAMILY_APPLY_NUM = 2;
	public static int[] familyLevelNeedTotalExp = new int[cfg.family.FamilyInfo.MAX_FAMILY_LEVEL + 1];
    public static volatile ArrayList<Integer> BLACK_MALL_LIST = new ArrayList<>();
    public static final List<cfg.mall.BlackMall> BLACK_MALL_CONF = new ArrayList<>();
	@Override
	public void start() {
		 int startexp = 0;
		 familyLevelNeedTotalExp[0] = 0;
		 familyLevelNeedTotalExp[1] = 0;
		 for(int level = 2; level <= cfg.family.FamilyInfo.MAX_FAMILY_LEVEL; level++){
			 startexp += CfgMgr.familyinfo.get(level - 1).requirebuildrate;
			 familyLevelNeedTotalExp[level] = startexp;
		 }
        CfgMgr.mall.values().forEach(e -> {
            if(e.getTypeId() == BlackMall.TYPEID){
                BLACK_MALL_CONF.add((BlackMall)e);
            }
        });
        regenerateBlackMall();

        new Procedure(){
            @Override
            protected boolean process() throws Exception {
                FFamily.FamilyName2id.putAll(FFamily.getFamilyNames());

                final long now = System.currentTimeMillis();
                for(long fid : FFamily.FamilyName2id.values()) {
                    final xbean.Family family = xtable.Family.get(fid);
                    if(family.getIssystemfam() == 1) {
                        FFamily.SysFamily2ChiefName.put(fid, family.getFamilyname());
                    }
                    if(!common.Time.isSameDay(family.getLastresettime(), now)) {
                        FFamily.dailyResetFamily(family, now);
                    }
                    FFamily.initFamilyAnimals(family);
                }
                return true;
            }
        }.call();

        //1min清理一次超时的众筹信息
//        xdb.Executor.getInstance().scheduleAtFixedRate(()->FFamily.clearCrowdInfo(), 0, 60, TimeUnit.SECONDS);
        //半个小时之后开始检测 自动创建家族
        xdb.Executor.getInstance().scheduleAtFixedRate(()->FFamily.checkBuildSystemFam(), 60 * 30, 60 * 30, TimeUnit.SECONDS);
        //45分钟之后开始批量加人，半个小时批一次
        xdb.Executor.getInstance().scheduleAtFixedRate(()->FFamily.acceptJoinSystemFamily(), 60 * 45, 60 * 30, TimeUnit.SECONDS);
        //第二天凌晨三点开始转移家族，每天批量转移一次
        long now = System.currentTimeMillis();
        long delayTime = common.Time.tomorrowZeroTime() + 3 * Time.HOUR_MILLISECONDS - now;
        xdb.Executor.getInstance().scheduleAtFixedRate(()->FFamily.sysFamTransChief(), delayTime, Time.DAY_MILLISECONDS , TimeUnit.MILLISECONDS);
        //每天晚上8点开始仙府聚宴
        long today8 = common.Time.todayZeroTime() + 20 * Time.HOUR_MILLISECONDS;
        long partyDelay;
        if(today8 >= now){
            partyDelay = today8 - now;
        }else {
            partyDelay = common.Time.tomorrowZeroTime() + 20 * Time.HOUR_MILLISECONDS - now;
        }
        xdb.Executor.getInstance().scheduleAtFixedRate(()->FFamily.openSysFamParty(), partyDelay, Time.DAY_MILLISECONDS, TimeUnit.MILLISECONDS);

	}

	private void regenerateBlackMall() {
        BLACK_MALL_LIST = new ArrayList<>(BLACK_MALL_CONF.stream().filter(c -> Utils.random01() <= c.openprobability).map(c -> c.id).collect(Collectors.toList()));
    }

	@Override
	public void onRoleCreateInProcedure(long roleid) {
		xtable.Rolefamily.insert(roleid, xbean.Pod.newRoleFamily());
	}

	@Override
	public void onRoleDeleteInProcedure(long roleid) {
//		xtable.Rolefamily.remove(roleid);
	}
	
	
	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		FFamily.sendFamilyInfo(roleid);
        xdb.Transaction.tsendWhileCommit(roleid, new SBlackMallList(BLACK_MALL_LIST));
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {
			
	}

	@Override
	public void onDayOver(long roleid) {
        new Procedure() {
            @Override
            protected boolean process() {
                xbean.RoleFamily roleFamily = FFamily.getRoleFamilyInfo(roleid);
                FFamily.resetFamilyParty(roleFamily);
                FMap.dispatchMessageInProcedure(roleid, new XResetRoleParty());
                roleFamily.getRequestedfamily().clear();
                return true;
            }
        }.execute();
	}

    @Override
    public void onDayOver() {
        regenerateBlackMall();
        Onlines.getInstance().broadcast(new SBlackMallList(BLACK_MALL_LIST));
        final long now = System.currentTimeMillis();
        for(long fid : FFamily.FamilyName2id.values()) {
            new xdb.Procedure() {
                @Override
                protected boolean process() {
                    final xbean.Family family = xtable.Family.get(fid);
                    if(family != null) {
                        FFamily.dailyResetFamily(family, now);
                    }
                    return true;
                }
            }.execute();
        }
    }
}
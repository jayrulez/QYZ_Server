package lx.gs.role;

import cfg.CfgMgr;
import cfg.currency.CurrencyType;
import common.AttrUtils;
import common.TaskQueue;
import gnet.link.Onlines;
import gnet.link.Role;
import gs.Config;
import gs.RoleAttrListener;
import lx.gs.event.CombatPowerChangeEvent;
import lx.gs.event.EventModule;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.role.msg.*;
import lx.gs.system.FSystem;
import map.msg.SChangeRawAttrs;
import xdb.Transaction;
import xdb.Xdb;
import xdb.logs.Listener;
import xdb.logs.Note;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public enum RoleModule implements gs.Module, gs.RoleLoginListener, RoleAttrListener, gs.GsdStartListener {
    INSTANCE;

    protected static Pattern senseWordPattern;
    protected static Pattern fuzzySenseWord;

    @Override
    public void afterGsdStart() {

    }

    @Override
    public void beforeGsdStop() {
        for(Role role : gnet.link.Onlines.getInstance().roles()){
            role.doCompleteLogout();
        }
    }


    private final static class Record {
        long sendTime;
        ArrayList<CurrencyAddRecord> records = new ArrayList<>();
    }

	private final Map<Long, Record> currencyChangeMap = new ConcurrentHashMap<>();


    private final static long FLUSH_TIME = 500;
    private final static long EXPIRE_TIME = 30000;

    @Override
    public void start() {
		updateSenseWords();

        registerRoleInfoListeners();
		registerRoleAttrListeners();

		TaskQueue.getScheduleExecutor().scheduleWithFixedDelay(this::flushCurrencyRecord, FLUSH_TIME, FLUSH_TIME, TimeUnit.MILLISECONDS);

		// 定期加体力
		final int addTiliInterval = CfgMgr.roleconfig.addonetiliinterval;
        final int maxTili = CfgMgr.roleconfig.maxtili;
		Xdb.getInstance().getExecutor().scheduleAtFixedRate(() -> {
			final long now = System.currentTimeMillis();
			for(gnet.link.Role role : Onlines.getInstance().roles()) {
				new xdb.Procedure() {
					@Override
					protected boolean process() {
						final long roleid = role.getRoleid();
						final xbean.RoleInfo info = xtable.Roleinfos.get(roleid);
                        if(info.getCurrencys().getOrDefault(CurrencyType.TiLi, 0L) >= maxTili){//只有自动增加不能超过上限，其余获取体力的方式都能超过上限
                            return true;
                        }
						info.setLastaddtilitime(now);
						FRole.addTiLiToLimit(roleid, info, 1, By.TiLi_Interval_Add);
						return true;
					}
				}.execute();
			}
		}, addTiliInterval, addTiliInterval, TimeUnit.SECONDS);
	}

	public void updateSenseWords(){
		senseWordPattern = Pattern.compile(FSystem.getSenseWords().stream().map(w -> Pattern.quote(w)).collect(Collectors.joining("|")));
	    fuzzySenseWord = Pattern.compile(FSystem.getFuzzyWords());
    }
    
    private void registerRoleInfoListeners() {
        
        xtable.Roleinfos.getTable().addListener(new Listener() {
			@Override
			public void onChanged(Object key) {	}

			@Override
			public void onRemoved(Object key) {	}

			@Override
			public void onChanged(Object key, String fullVarName, Note note) {
				final Long roleid = (Long)key;
				final xbean.RoleInfo info = xtable.Roleinfos.get(roleid);
				Onlines.getInstance().send(roleid, new SVipExpChange(info.getVipexp(), info.getViplevel()));
				xdb.Trace.debug("role.listener exp change. roleid:{} vipexp:{} viplevel:{}",
					roleid, info.getVipexp(), info.getViplevel());
			}
        	
        }, "value", "vipexp");

        xtable.Roleinfos.getTable().addListener(new Listener() {
			@Override
			public void onChanged(Object key) {	}

			@Override
			public void onRemoved(Object key) {	}

			@Override
			public void onChanged(Object key, String fullVarName, Note note) {
				final Long roleid = (Long)key;
				final xbean.RoleInfo info = xtable.Roleinfos.get(roleid);
				final Map<Integer, Long> currencys = info.getCurrencys();
				@SuppressWarnings("unchecked")
				xdb.logs.NoteMap<Integer, Long> nmap = (xdb.logs.NoteMap<Integer, Long>)note;
				final SCurrencyChange re = new SCurrencyChange();

				for(int type : nmap.getAdded()) {//新加的货币总类
					re.currencys.put(type, currencys.get(type));
				}
				for(int type : nmap.getReplaced().keySet()) {//发生改变的货币种类,发生这两种变化时向客户端通知
					re.currencys.put(type, currencys.get(type));
				}

                if(!re.currencys.isEmpty()) {
                    Onlines.getInstance().send(roleid, re);
                    xdb.Trace.debug("role.listener currency change. roleid:{} currencys:{}", roleid, re.currencys);
                }
			}

        }, "value", "currencys");
    }

	private void registerRoleAttrListeners() {
		xtable.Roleattrs.getTable().addListener(new xdb.logs.Listener() {
			@Override
			public void onChanged(Object key) {

			}

			@Override
			public void onRemoved(Object key) {

			}

			@Override
			public void onChanged(Object key, String fullVarName, Note note) {
				new xdb.Procedure() {
					@Override
					protected boolean process() {
						Long roleid = (Long)key;
						final xbean.RoleAttr info = xtable.Roleattrs.get(roleid);
						FRoleAttr.updateRoleCombatPower(info);
						FMap.dispatchMessageInProcedure(roleid, new SChangeRawAttrs(info.getResethpmp(), info.getTotalcombatpower(), new ArrayList<>(info.getRawattrs())));
						info.setResethpmp(0);
						return true;
					}
				}.execute();

			}
		}, "value", "groupattrs");

        xtable.Roleattrs.getTable().addListener(new xdb.logs.Listener() {
            @Override
            public void onChanged(Object key) {
            }

            @Override
            public void onRemoved(Object key) {

            }

            @Override
            public void onChanged(Object key, String fullVarName, Note note) {
                new xdb.Procedure() {
                    @Override
                    protected boolean process() {
                        final Long roleid = (Long)key;
                        final xbean.RoleAttr info = xtable.Roleattrs.get(roleid);
                        final int totalCombatPower = info.getTotalcombatpower();
                        final SChangeAttrs msg = new SChangeAttrs();
                        msg.combatpower = totalCombatPower;
                        AttrUtils.convert(info.getFinalattrs(), msg.attrs);
                        xdb.Transaction.tsendWhileCommit(roleid, msg);
						EventModule.INSTANCE.broadcastEvent(new CombatPowerChangeEvent(roleid, totalCombatPower));
						return true;
                    }
                }.execute();
            }
        }, "value", "totalcombatpower");

	}



	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		FRole.refreshTili(roleid);
		final xbean.RoleConfigure conf = FRole.getConfigure(roleid);
		xdb.Transaction.tsend(roleid, new SGetConfigures(new HashMap<>(conf.getDatas())));
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {
		
	}


	@Override
	public void updateRoleAttr(long roleid) {
		final xbean.RoleInfo ri = xtable.Roleinfos.get(roleid);
		final cfg.fight.Attr attr = CfgMgr.basicstatus.get(ri.getProfession()).levelstatusinfo_level.get(ri.getLevel()).attr;
		final HashMap<Integer, Float> attrs = new HashMap<>();
		common.AttrUtils.convert(attr, attrs);
		FRoleAttr.updateGroupAndResetHPMP(roleid, "level", attrs, true);
	}

	public void recordCurrencyAdd(long roleid, int currencyType, long val){
		Transaction.texecuteWhileCommit(() -> {
			final Map<Long, Record> currencyMap = currencyChangeMap;
			Record record = currencyMap.get(roleid);
			if(record == null) {
				record = new Record();
				final Record oldRecord = currencyMap.putIfAbsent(roleid, record);
				if(oldRecord != null)
					record = oldRecord;
			}
			synchronized (record) {
				if(record.records.isEmpty())
					record.sendTime = System.currentTimeMillis();
				record.records.add(new CurrencyAddRecord(currencyType, (int)val));
			}
		});
	}

    public void getThenClearCurrencyAddRecords(long roleid, SKillMonster msg) {
        final Map<Long, Record> currencyMap = currencyChangeMap;
        Record record = currencyMap.get(roleid);
        if(record != null) {
            synchronized (record) {
                if(!record.records.isEmpty()) {
                    record.sendTime = System.currentTimeMillis();
                    final ArrayList<CurrencyAddRecord> temp = msg.currencys;
                    msg.currencys = record.records;
                    record.records = temp;
                    record.sendTime = System.currentTimeMillis();
                }
            }
        }
    }

	public void flushCurrencyRecord(){
        final long now = System.currentTimeMillis();

		currencyChangeMap.forEach((roleid, info) -> {
            SCurrencyAlert sAlert = null;
            synchronized (info) {
                if(!info.records.isEmpty()) {
                    if(info.sendTime + FLUSH_TIME < now) {
                        sAlert = new SCurrencyAlert(info.records);
                        info.records = new ArrayList<>();
                    }
                } else if(info.sendTime + EXPIRE_TIME < now){
                    currencyChangeMap.remove(roleid);
                }
            }
            if(sAlert != null)
                Onlines.getInstance().send(roleid, sAlert);
		});
	}



}

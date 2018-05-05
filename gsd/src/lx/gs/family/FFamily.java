package lx.gs.family;

import cfg.CfgMgr;
import cfg.family.FamilyJobEnum;
import common.ErrorCode;
import common.Time;
import gs.RefObject;
import lx.gs.event.EventModule;
import lx.gs.event.FamilyBuildEvent;
import lx.gs.event.FamilyCreateEvent;
import lx.gs.family.msg.*;
import lx.gs.leaderboard.LeaderBoardModule;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.system.FSystem;
import map.msg.SChangeFamily;
import xbean.Family;
import xbean.FamilyMember;
import xdb.Lockeys;
import xdb.Procedure;
import xdb.Transaction;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class FFamily {
	public static ConcurrentHashMap<Long, Long> FamilyId2MapId = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Long, String> SysFamily2ChiefName = new ConcurrentHashMap<>();
    //家族名字对应家族id
    public static ConcurrentHashMap<String, Long> FamilyName2id = new ConcurrentHashMap<>();


	/**
	 * 创建一个家族成员
	 * @param roleid
	 * @return
	 */
	public static xbean.FamilyMember createMemeberinfo(long roleid){
		long curtime = System.currentTimeMillis();
		xbean.FamilyMember newm = xbean.Pod.newFamilyMember();
		newm.setFamilyjob(cfg.family.FamilyJobEnum.MEMBER);
		newm.setJointime(curtime);
		newm.setRoleid(roleid);
		return newm;
	}

	public static void dailyResetFamily(xbean.Family family, long now) {
        family.getRequestinglist().clear();
        family.getMembers().values().forEach(m -> m.setDailybuild(0));
        family.setLastresettime(now);
    }
	
	
	/**
	 * 获取家族信息
	 * @param familyid
	 * @return
	 */
	public static xbean.Family getFamilyByFamilyId(long familyid){
		return xtable.Family.get(familyid);
	}

	/**
	 * 根据角色ID获取家族信息
	 * @param roleid
	 * @return
	 */
	public static xbean.Family getFamilyByRoleId(long roleid){
		xbean.RoleFamily e = FFamily.getRoleFamilyInfo(roleid);
		if(e.getCurrentfid() > 0){
			return FFamily.getFamilyByFamilyId(e.getCurrentfid());
		}
		return null;
	}

    public static xbean.Family selectFamilyByRoleId(long roleid){
        xbean.RoleFamily e = FFamily.getRoleFamilyInfo(roleid);
        if(e.getCurrentfid() > 0){
            return xtable.Family.select(e.getCurrentfid());
        }
        return null;
    }

	/**
	 * 根据角色ID获取玩家的家族id
	 */
	public static long getFamilyIdByRoleId(long roleid){
		return xtable.Rolefamily.selectCurrentfid(roleid);
	}
	
	public static String makeFullname(String name){
		return gs.Utils.makeFullName(name, gs.Utils.getServerId());
	}

	public static Map<String, Long> getFamilyNames(){
		xbean.FNameToID data = xtable.Familyname2id.get(0);
		if(data == null){
			data = xbean.Pod.newFNameToID();
			xtable.Familyname2id.insert(0, data);
		}
		return data.getData();
	}

	/**
	 * 根据名字查找包含该名字的家族id
	 * @param name
	 * @return
	 */
	public static List<Long> findFamidsByname(String name){
		return FamilyName2id.entrySet().stream().filter(e-> e.getKey().contains(name)).map(e -> e.getValue()).collect(Collectors.toList());
	}
	
	/**
	 * 获取角色的家族信息，如果为空，创建一个新的。
	 * @param roleid
	 * @return
	 */
	public static xbean.RoleFamily getRoleFamilyInfo(long roleid){
		return xtable.Rolefamily.get(roleid);
	}

    public static void sendFamilyInfo(long roleid) {
        SGetFamilyInfo result = new SGetFamilyInfo();
        xbean.RoleFamily roleFamily = getRoleFamilyInfo(roleid);
        if (roleFamily.getCurrentfid() != 0) {
            xtable.Family.getTable().select(roleFamily.getCurrentfid(), family -> {
                result.family = FFamily.makeProtocolFamilyBasicInfo(family);
                xbean.FamilyMember self = family.getMembers().get(roleid);
                result.selfinfo = makeProtocolFamilyMember(roleid, self);
                return family;
            });
        }
        xdb.Transaction.tsendWhileCommit(roleid, result);
    }

    /**
	 * 检查族长是否10天之内未上线，如果是转移族长身份给其他人员
	 * @param roleid
	 */
	public static void checkFamilyChiefOnline(long roleid, xbean.Family family){
		if(roleid <= 0) return;
		if(family == null) return ;
		long chiefid = family.getChiefid();
		xbean.RoleInfo ci = xtable.Roleinfos.select(chiefid);
		long offday = common.Time.intervalDays(ci.getLastlogintime(), System.currentTimeMillis());
		if(offday >= FFamilyModule.MAX_OFFLINE_DAY){
			FFamily.transferFamilyChief(family, 0);
		}
	}

	/**
	 * 花费家族资金
	 * @param roleid
	 * @param money
	 * @return
	 */
	public static ErrorCode costFamilyMoneyByRole(long roleid, long money, By by){
		xdb.Trace.info("FFamily: costFamilyMoneyByRole, roleid={}, money={}, by={}", roleid, money, by);
		xbean.Family family = FFamily.getFamilyByRoleId(roleid);
		if(!FFamily.isFamilyLeader(roleid, family)) return ErrorCode.ONLY_CHIEF_VICECHIEF_CAN_ACTION;
		if(family.getMoney() < money) return ErrorCode.FAMILY_MONEY_NOT_ENOUGH;
		family.setMoney(family.getMoney()-money);
		family.setUpdatetime(System.currentTimeMillis());
		
		FFamily.sendFamilyInfoChangeNotify(family);
		return ErrorCode.OK;
	}
	/**
	 * 增加家族的建设度和资金
	 * @param roleid
	 * @param money
	 * @param build
	 * @return
	 */
	public static ErrorCode addFamilyBuildAndMoneyByRole(long roleid, long money, int build){
		xdb.Trace.info("FFamily: addFamilyBuildAndMoneyByRole, roleid={}, money={}, build={}", roleid, money, build);
		xbean.Family family = FFamily.getFamilyByRoleId(roleid);
		xbean.FamilyMember member = family.getMembers().get(roleid);
		if(build > 0){
			family.setTotalbuilddegree(family.getTotalbuilddegree() + build);
			addBuildGrade(roleid, family, build); //提高家族贡献度,可能会有等级提升
			member.setPersonalbuild(member.getPersonalbuild() + build);
            member.setDailybuild(member.getDailybuild() + build);
            EventModule.INSTANCE.broadcastEvent(new FamilyBuildEvent(roleid, family.getFamilyid(), family.getTotalbuilddegree()));
		}
		family.setMoney(family.getMoney()+money);
		family.setUpdatetime(System.currentTimeMillis());
		
		FFamily.sendFamilyInfoChangeNotify(family);
		return ErrorCode.OK;
	}
	
	public static void addBuildGrade(long roleid, xbean.Family family, int addBuild){
		final int oldExp = family.getCurlvlbuilddegree();
		int newExp = oldExp + addBuild;

		final int oldLevel = family.getFlevel();
		int newLevel = oldLevel;
		while(newLevel < cfg.family.FamilyInfo.MAX_FAMILY_LEVEL) {//类似与dota中显示经验的方式
			final cfg.family.FamilyInfo ecfg = CfgMgr.familyinfo.get(newLevel);
			if(ecfg.requirebuildrate > newExp) break;
            if(ecfg.requirecapitanlevel.level > xtable.Roleinfos.selectLevel(family.getChiefid())){//加了一个家族族长等级限制
                break;
            }
			newExp -= ecfg.requirebuildrate;
			newLevel++;
		}

		family.setCurlvlbuilddegree(newExp);
		if (newLevel != oldLevel) {
			family.setFlevel(newLevel);
            addFamilyLog(family, roleid, FamilyLogReport.TYPE_UPLEVEL_FAMILY, newLevel);
		}
	}
	
	/**
	 * 通知整个家族	
	 * @param
	 */
	static void sendFamilyInfoChangeNotify(xbean.Family familyInfo){
		SFamilyInfoChangeNotify notify = new SFamilyInfoChangeNotify();
		notify.declaration =familyInfo.getDeclaration();
        notify.publicinfo = familyInfo.getPublicinfo();
        notify.curlvlbuilddegree = familyInfo.getCurlvlbuilddegree();
        notify.money = familyInfo.getMoney();
        notify.flevel = familyInfo.getFlevel();
		FFamily.multicastAllFamily(familyInfo, notify);
	}

	public static boolean multicastAllFamily(long fid, xio.Protocol pro){
		xbean.Family family = FFamily.getFamilyByFamilyId(fid);
		if(family == null) return false;
		return multicastAllFamily(family, pro);
	}
	
	public static boolean multicastAllFamily(xbean.Family family, xio.Protocol pro){
		Transaction.tsendWhileCommit(family.getMembers().keySet(), pro);
		return true;
	}

    public static boolean isFamilyFull(xbean.Family family){
        int total = cfg.CfgMgr.familyinfo.get(family.getFlevel()).memberamount;
        return family.getMembers().size() >= total;
    }

    public static int leftPositions(xbean.Family family){
        int total = cfg.CfgMgr.familyinfo.get(family.getFlevel()).memberamount;
        return total - family.getMembers().size();
    }

    public static boolean isCanApply(long fid){
        RefObject<Boolean> canApply = new RefObject<>(false);
        xtable.Family.getTable().select(fid, family -> {
            canApply.value = family.getRequestinglist().size() < FFamily.leftPositions(family);
            return family;
        });
        return canApply.value;
    }

	public static xbean.FamilyLogReport addFamilyLog(xbean.Family family, long roleid, int type, int num ){
		xbean.FamilyLogReport rp = xbean.Pod.newFamilyLogReport();
		rp.setActiontime(System.currentTimeMillis());
		rp.setActiontype(type);
		rp.setNumber(num);
		rp.setRoleid(roleid);
		//检查日志记录是否满了，如果满从0开始删除，取得时候倒序取，能够保证按时间排序

        List<xbean.FamilyLogReport> logs = family.getLogs();
        if(logs.size() >= cfg.family.FamilyInfo.MAX_LOG_SIZE){
            logs.remove(0);
        }
		family.getLogs().add(rp);
		return rp;
	}

	/**
	 * 退出家族两小时内无法做某些操作
	 * @param roleid
	 * @param hour
	 * @return
	 */
	public static boolean isInQuitTimeLimit(long roleid, int hour){
		xbean.RoleFamily info = FFamily.getRoleFamilyInfo(roleid);
		return isInQuitTimeLimit(info, hour);
	}
	public static boolean isInQuitTimeLimit(xbean.RoleFamily info, int hour){
		if(info.getLastquittime() <= 0 ) return false;
		long curtime = System.currentTimeMillis();
        return (curtime - info.getLastquittime()) <= (TimeUnit.HOURS.toMillis(hour)) &&
                info.getTotalquitnum() > cfg.family.FamilyInfo.MAX_QUIT_NUM;
    }
	
	public static boolean isFamilyMember(xbean.Family info, long roleid){
        return info.getMembers().containsKey(roleid);
    }

	public static boolean isFamilyChief(long roleid, xbean.Family family){
        return family.getChiefid() == roleid;
    }

    public static boolean isFamilyLeader(long roleid, xbean.Family finfo){
        return finfo.getMembers().get(roleid).getFamilyjob() > FamilyJobEnum.MEMBER;
    }

    public static void initFamilyAnimals(xbean.Family family) {
        final Map<Integer, xbean.FamilyGodAnimal> bosses = family.getActivity().getGodanimalinfo();
        for(cfg.family.Boss bcfg : CfgMgr.boss.values()) {
            final int bossid = bcfg.bossid;
            xbean.FamilyGodAnimal boss = bosses.get(bossid);
            if(boss == null) {
                boss = xbean.Pod.newFamilyGodAnimal();
                boss.setAnimalid(bossid);
                boss.setAnimallevel(1);
                bosses.put(bossid, boss);
            }
        }
    }

	public static boolean initFamilyJobInfo(xbean.Family family){
		Map<Integer,xbean.FamilyJobStaffList> jobs = family.getJobinfo();
		jobs.put(cfg.family.FamilyJobEnum.CHIEF, xbean.Pod.newFamilyJobStaffList());
		jobs.put(cfg.family.FamilyJobEnum.VICE_CHIEF, xbean.Pod.newFamilyJobStaffList());
		jobs.put(cfg.family.FamilyJobEnum.QING_LONG_SHI, xbean.Pod.newFamilyJobStaffList());
		jobs.put(cfg.family.FamilyJobEnum.XUAN_WU_SHI, xbean.Pod.newFamilyJobStaffList());
		jobs.put(cfg.family.FamilyJobEnum.YOU_HU_FA, xbean.Pod.newFamilyJobStaffList());
		jobs.put(cfg.family.FamilyJobEnum.ZHU_QUE_SHI, xbean.Pod.newFamilyJobStaffList());
		jobs.put(cfg.family.FamilyJobEnum.ZUO_HU_FA, xbean.Pod.newFamilyJobStaffList());
		jobs.put(cfg.family.FamilyJobEnum.BAI_HU_SHI, xbean.Pod.newFamilyJobStaffList());
		return true;
	}

	public static boolean isFamilyUnLocked(long roleid){
        return xtable.Roleinfos.selectLevel(roleid) >= cfg.family.FamilyInfo.OPEN_LEVEL;
    }

	private static boolean isJobStaffFull(xbean.Family family, int jobtype){
		if(jobtype == cfg.family.FamilyJobEnum.MEMBER) return false;
		cfg.family.FamilyJob conf = cfg.CfgMgr.familyjob.get(jobtype);
		xbean.FamilyJobStaffList list = family.getJobinfo().get(jobtype);
		int cursize = list.getStaffs().size();
		int level = family.getFlevel();
		return conf.amount.get(level - 1) <= cursize;
	}

	//删除该职位的人员
	static void removeJobStaff(xbean.Family family, int jobtype, long roleid){
		if(jobtype == cfg.family.FamilyJobEnum.MEMBER) return;
		xbean.FamilyJobStaffList stafflist = family.getJobinfo().get(jobtype);
		stafflist.getStaffs().remove(roleid); 
		xbean.FamilyMember fm = family.getMembers().get(roleid);
		fm.setFamilyjob(cfg.family.FamilyJobEnum.MEMBER);
	}

	//任命roleid为该职位，如果职位已满，返回fasle
	public static boolean addJobStaff(xbean.Family family, int jobtype, long roleid){
		if(jobtype == cfg.family.FamilyJobEnum.MEMBER) return false;
		if(isJobStaffFull(family, jobtype)) return false;
		xbean.FamilyJobStaffList list = family.getJobinfo().get(jobtype);
		list.getStaffs().put(roleid, System.currentTimeMillis());
		xbean.FamilyMember fm = family.getMembers().get(roleid);
		fm.setFamilyjob(jobtype);
		return true;
	}

	//通知族长和副族长
	public static boolean sendNotifyToChiefViceChief(xbean.Family family, xio.Protocol pro){
	    List<Long> receivers = new ArrayList<>();
        receivers.add(family.getChiefid());

        receivers.addAll(family.getJobinfo().get(cfg.family.FamilyJobEnum.VICE_CHIEF).getStaffs().keySet());
        Transaction.tsendWhileCommit(receivers, pro);
		return true;
	}

	/**
	 * 解除职位信息
	 * @param family
	 * @param roleid
	 */
	public static void releaseFamilyJob(xbean.Family family, long roleid){
		if(FFamily.isFamilyMember(family, roleid)){
			xbean.FamilyMember mem = family.getMembers().get(roleid);
			FFamily.removeJobStaff(family, mem.getFamilyjob(), roleid);
			mem.setFamilyjob(cfg.family.FamilyJobEnum.MEMBER);
		}
	}

	/**
	 * 转移族长，如果就剩下一个人，无法转移，如果isauto是true，自动选择家族贡献度大的而且近期上过线的人，
	 * @param family
	 * @return
	 */
	public static boolean transferFamilyChief(xbean.Family family, long roleid){
		long newid = roleid;
        Map<Long, xbean.FamilyMember> members = family.getMembers();
        final long chiefid = family.getChiefid();
        final long now = System.currentTimeMillis();
		if(roleid == 0 && members.size() > 1) {
            newid = Collections.max(members.values(), (m1, m2) -> {
                final long roleid1 = m1.getRoleid();
                final long roleid2 = m2.getRoleid();

                if(roleid1 == chiefid) return -1;
                if(roleid2 == chiefid) return 1;

                boolean recentLogin1 = common.Time.intervalDays(xtable.Roleinfos.selectLastlogintime(roleid1), now)
                        <= cfg.family.FamilyInfo.ONLINE_DAY_NUM;
                boolean recentLogin2 = common.Time.intervalDays(xtable.Roleinfos.selectLastlogintime(roleid2), now)
                        <= cfg.family.FamilyInfo.ONLINE_DAY_NUM;
                if(!recentLogin1 && recentLogin2) return -1;
                if(recentLogin1 && !recentLogin2) return 1;
                return Long.compare(m1.getPersonalbuild(), m2.getPersonalbuild());
            }).getRoleid();
		}
		if(newid == 0 || newid == chiefid) return false;
		xdb.Trace.info("FFamily:transferFamilyChief oldchief={}, newchief={}", chiefid, newid);
		xbean.FamilyMember oldchief = members.get(chiefid);
		xbean.FamilyMember newchief = members.get(newid);
		
		FFamily.removeJobStaff(family, newchief.getFamilyjob(), newid);
		
		family.setChiefid(newid);
		family.setUpdatetime(now);
		removeJobStaff(family,cfg.family.FamilyJobEnum.CHIEF, chiefid);
		addJobStaff(family, cfg.family.FamilyJobEnum.CHIEF, newid);
		
		oldchief.setFamilyjob(cfg.family.FamilyJobEnum.MEMBER);
		newchief.setFamilyjob(cfg.family.FamilyJobEnum.CHIEF);
		
		
		//发送通知
		STransferChiefNotify notify = new STransferChiefNotify();
		notify.operator = FFamily.makeProtocolFamilyMember(chiefid, oldchief);
		notify.jobid = cfg.family.FamilyJobEnum.CHIEF;
		notify.member = FFamily.makeProtocolFamilyMember(newid, newchief);
		
		//记录日志
		FFamily.addFamilyLog(family, newid, lx.gs.family.msg.FamilyLogReport.TYPE_CHIEF_TRANSFER, 0);
		//往家族频道发送通知消息族长更换
		multicastAllFamily(family, notify);
		return true;		
	}
	
	private static void sendNotify(long roleid, xio.Protocol pro){
		xdb.Transaction.tsendWhileCommit(roleid, pro);
	}	

	public static lx.gs.family.msg.FamilyMember makeProtocolFamilyMember(long roleid, xbean.FamilyMember member){
		lx.gs.family.msg.FamilyMember newm = new lx.gs.family.msg.FamilyMember();
		newm.roleid = roleid;
		newm.jointime = member.getJointime();
		newm.familyjob = member.getFamilyjob();
        newm.attackpower = xtable.Roleattrs.selectTotalcombatpower(roleid);//战斗力
        newm.pcontribution = member.getPersonalbuild();
        newm.dailybuild = member.getDailybuild();
        newm.totalquitnum = xtable.Rolefamily.selectTotalquitnum(roleid);

        xtable.Roleinfos.getTable().select(roleid, role -> {
            newm.rolename = role.getName();
            newm.lastonlinetime = role.getLastlogouttime();
            newm.isonline = gnet.link.Onlines.getInstance().find(roleid) != null ? 1 : 0;
            newm.level = role.getLevel();

            newm.viplevel = role.getViplevel();
            newm.professiontype = role.getProfession();
            newm.gender = role.getGender();
            return role;
        });

		return newm;
	}

    public static lx.gs.family.msg.FamilyBasicInfo makeProtocolFamilyBasicInfo(long familyid) {
        lx.gs.family.msg.FamilyBasicInfo info = xtable.Family.getTable().select(familyid, family -> {
            return makeProtocolFamilyBasicInfo(family);
        });
        return info != null ? info : new FamilyBasicInfo();
    }
	
	public static lx.gs.family.msg.FamilyBasicInfo makeProtocolFamilyBasicInfo(xbean.Family f) {
        lx.gs.family.msg.FamilyBasicInfo info = new lx.gs.family.msg.FamilyBasicInfo();
        info.familyid = f.getFamilyid();
        info.familyname = f.getFamilyname();
        info.flevel = f.getFlevel();
        info.money = f.getMoney();
        info.curlvlbuilddegree = f.getCurlvlbuilddegree();
        info.totalbuilddegree = f.getTotalbuilddegree();
        info.totaobanggong = f.getTotalbanggong();
        info.declaration = f.getDeclaration();
        info.publicinfo = f.getPublicinfo();
        info.malllevel = f.getMalllevel();
        info.membernum = f.getMembers().size();
        info.publictime = f.getPublictime();
        info.chiefid = f.getChiefid();
        xtable.Roleinfos.getTable().select(f.getChiefid(), roleInfo -> {
            info.chiefname = roleInfo.getName();
            info.chieflvl = roleInfo.getLevel();
            return roleInfo;
        });

        info.godanimalstarttime = f.getBeatanimalactivity().getStarttime();
        info.godanimalendtime = f.getBeatanimalactivity().getEndtime();
        if (!f.getRequestinglist().isEmpty()) {
            info.familyrequestinfo = 1;//如果申请列表不为空
        } else {
            info.familyrequestinfo = 0;
        }
        info.familypartylastopentime = f.getLastpartyopentime();
        info.familypartylastcalltime = f.getLastpartycalltime();
        return info;
    }

    public static lx.gs.family.msg.FamilyLogReport makeProtocolFamilyLog(xbean.FamilyLogReport fr){
		lx.gs.family.msg.FamilyLogReport r = new lx.gs.family.msg.FamilyLogReport();
		r.actiontime = fr.getActiontime();
		r.actiontype = fr.getActiontype();
		r.number = fr.getNumber();
		r.roleid = fr.getRoleid();
        r.rolename = xtable.Roleinfos.selectName(fr.getRoleid());
		return r;
	}
	
    public static lx.gs.family.msg.FamilyGodAnimal makeProtocolFamilyAnimal(xbean.FamilyGodAnimal g){
		lx.gs.family.msg.FamilyGodAnimal an = new lx.gs.family.msg.FamilyGodAnimal();
		an.animalid = g.getAnimalid();
		an.animallevel = g.getAnimallevel();
		an.exp = g.getExp();
		return an;
	}

	

	public static void onLeaveFamily(long roleid) {
		final SChangeFamily msg = new SChangeFamily("");
		FMap.dispatchMessageInProcedure(roleid, msg);
	}
	
	public static void onJoinFamily(xbean.Family family, xbean.FamilyMember member) {
		final SChangeFamily msg = new SChangeFamily(family.getFamilyname());
		FMap.dispatchMessageInProcedure(member.getRoleid(), msg);
	}

    public static void resetFamilyParty(xbean.RoleFamily roleFamily){
        roleFamily.setHasjoinpartytoday(0);
    }

    public static void checkBuildSystemFam(){
        new Procedure(){
            @Override
            protected boolean process() throws Exception {
                xbean.System system = FSystem.get();
                int curFamilySize = LeaderBoardModule.familyRankMap.size();
                int lackFamily = system.getRolenumsreach20() / 50 - curFamilySize;
                if(lackFamily < 5){
                    return true;
                }
                ;//做了一个限制，最多同时创建10个系统家族
                for(int needCreatFams = Math.min(lackFamily, 10) ; needCreatFams > 0 && system.getMaxsystemfamnum() > 0 ; needCreatFams--){
                    String sysFamilyName = randomFamilyName();
                    if(sysFamilyName.length() == 0){
                        //如果正好随不到名字，那么就继续下一次循环，本次少创建一个家族
                        continue;
                    }
                    String fullName = FFamily.makeFullname(sysFamilyName);
                    long curtime = java.lang.System.currentTimeMillis();
                    xbean.Family newf = xbean.Pod.newFamily();
                    long id = xtable.Family.insert(newf);
                    newf.setFamilyid(id);
                    newf.setFamilyname(sysFamilyName);
                    newf.setUpdatetime(curtime);
                    newf.setCreatetime(curtime);
                    newf.setFlevel(1);
                    newf.setMalllevel(1);
                    newf.setDeclaration(cfg.family.FamilyInfo.DEFAULT_DECLARATION);
                    newf.setPublicinfo(cfg.family.FamilyInfo.DEFAULT_PUBLICINFO);
                    newf.setIssystemfam(1);
                    FFamily.getFamilyNames().put(fullName, id);
                    long chiefRoleid = FSystem.createFakeFamilyChief();
                    xbean.FamilyMember member = xbean.Pod.newFamilyMember();
                    member.setFamilyjob(FamilyJobEnum.CHIEF);
                    member.setJointime(curtime);
                    member.setRoleid(chiefRoleid);
                    newf.getMembers().put(chiefRoleid, member);
                    newf.setChiefid(chiefRoleid);
                    FFamily.initFamilyJobInfo(newf);
                    FFamily.addJobStaff(newf, FamilyJobEnum.CHIEF, chiefRoleid);
                    FFamily.initFamilyAnimals(newf);
                    FFamily.addFamilyLog(newf, chiefRoleid, FamilyLogReport.TYPE_CREATE_FAMILY,0);
                    SysFamily2ChiefName.put(id, sysFamilyName);
                    EventModule.INSTANCE.broadcastEvent(new FamilyCreateEvent(chiefRoleid, newf.getFamilyid(), fullName));
                    system.setMaxsystemfamnum(system.getMaxsystemfamnum() - 1);//最大创建200个系统家族
                }
                return true;
            }
        }.call();

    }

    public static void acceptJoinSystemFamily() {
        for (Entry<Long, String> famEntry : SysFamily2ChiefName.entrySet()) {
            new Procedure() {
                @Override
                protected boolean process() throws Exception {
                    final long familyid = famEntry.getKey();
                    xbean.Family sysFamilyInfo = xtable.Family.get(familyid);
                    int totalMembers = cfg.CfgMgr.familyinfo.get(sysFamilyInfo.getFlevel()).memberamount;
                    if (totalMembers <= sysFamilyInfo.getMembers().size()) {
                        return false;
                    }
                    Set<Long> notifyRoleids = new HashSet<Long>();
                    for (Iterator<Long> iterator = sysFamilyInfo.getRequestinglist().keySet().iterator(); iterator.hasNext(); ) {
                        long roleid = iterator.next();
                        iterator.remove();
                        xbean.RoleFamily roleInfo = FFamily.getRoleFamilyInfo(roleid);
                        if (roleInfo.getCurrentfid() == 0 && roleInfo.getRequestedfamily().remove(familyid) != null) {
                            roleInfo.setCurrentfid(familyid);
                            if (roleInfo.getRequestedfamily().size() > 0) {
                                for(long fid : roleInfo.getRequestedfamily().keySet()) {
                                    Transaction.texecuteWhileCommit(new xdb.Procedure() {
                                        @Override
                                        protected boolean process() {
                                            xbean.Family familyInfo = FFamily.getFamilyByFamilyId(fid);
                                            if (familyInfo != null) {
                                                familyInfo.getRequestinglist().remove(roleid);
                                            }
                                            return true;
                                        }
                                    });
                                }
                                roleInfo.getRequestedfamily().clear();
                            }
                            xbean.FamilyMember newm = FFamily.createMemeberinfo(roleid);
                            sysFamilyInfo.getMembers().put(roleid, newm);
                            //记录日志
                            FFamily.addFamilyLog(sysFamilyInfo, roleid, lx.gs.family.msg.FamilyLogReport.TYPE_JOIN_FAMILY, 0);
                            FFamily.onJoinFamily(sysFamilyInfo, newm);
                            notifyRoleids.add(roleid);
                            if (sysFamilyInfo.getMembers().size() >= totalMembers)
                                break;
                        }
                    }
                    lx.gs.family.msg.FamilyBasicInfo familyBasicInfo = null;
                    if (notifyRoleids.size() > 0) {
                        familyBasicInfo = FFamily.makeProtocolFamilyBasicInfo(sysFamilyInfo);
                    }
                    for (long roleid : notifyRoleids) {
                        SAcceptRequestJoinFNotify notify = new SAcceptRequestJoinFNotify();
                        notify.family = familyBasicInfo;
                        notify.member = FFamily.makeProtocolFamilyMember(roleid, sysFamilyInfo.getMembers().get(roleid));
                        xdb.Transaction.tsendWhileCommit(roleid, notify);
                    }
                    return true;
                }
            }.call();
        }


    }

    public static void sysFamTransChief(){
        Set<Long> transSuccFamilyIds = new HashSet<>();
        for(long familyid : SysFamily2ChiefName.keySet()){
            new Procedure(){
                @Override
                protected boolean process() throws Exception {
                    Family sysFamilyInfo = xtable.Family.get(familyid);
                    long newChief = findMaxcombatpower(sysFamilyInfo.getMembers().keySet(), sysFamilyInfo.getChiefid());
                    if(newChief != 0 && newChief != sysFamilyInfo.getChiefid() && sysFamilyInfo.getIssystemfam() == 1){
                        FFamily.removeJobStaff(sysFamilyInfo, FamilyJobEnum.CHIEF, sysFamilyInfo.getChiefid());
                        FFamily.addJobStaff(sysFamilyInfo, FamilyJobEnum.CHIEF, newChief);
                        sysFamilyInfo.getMembers().remove(sysFamilyInfo.getChiefid());//移除机器人族长
                        sysFamilyInfo.setChiefid(newChief);
                        sysFamilyInfo.setIssystemfam(0);
                        transSuccFamilyIds.add(familyid);
                    }
                    return true;
                }
            }.call();
        }
        for(long fid : transSuccFamilyIds){
            SysFamily2ChiefName.remove(fid);
        }
    }

    public static long findMaxcombatpower(Set<Long> roleids, long sysChief){
        long result = 0;
        int combatpower = 0;
        for(long roleid : roleids){
            if(roleid == sysChief)
                continue;
            int power = xtable.Roleattrs.selectTotalcombatpower(roleid);
            if(power >= combatpower){
                combatpower = power;
                result = roleid;
            }
        }
        return result;
    }

    public static String randomFamilyName(){
        Map<String, Long> familyNames = FFamily.getFamilyNames();
        String name = "";
        cfg.family.FamilyName conf = CfgMgr.familyname;
        for(int i = 0 ; i < 20 ; i++) {
            int firstIndex = common.Utils.randomRange(0, conf.firstname.size());
            int lastIndex = common.Utils.randomRange(0, conf.lastname.size());
            name = conf.firstname.get(firstIndex) + conf.lastname.get(lastIndex);
            final String fullName = makeFullname(name);
            if(!familyNames.containsKey(fullName)){
                return name;
            }
        }
        return name;
    }

    /*
     *随机选系统家族,如果系统家族太少了，直接遍历选
     */
    public static Set<Long> randomChooseTwoSysFams(int num){
        Set<Long> fams = new HashSet<>();
        if(num * 2 >= SysFamily2ChiefName.size()) {
            for (long fid : SysFamily2ChiefName.keySet()) {
                if (num <= 0) {
                    break;
                }
                if (isCanApply(fid)) {
                    fams.add(fid);
                    num--;
                }
            }
        } else {
            Long[] keys = SysFamily2ChiefName.keySet().toArray(new Long[SysFamily2ChiefName.size()]);
            for (int i = 0; i < 20; i++) {
                int randomIndex = common.Utils.randomRange(0, keys.length);
                long fid = keys[randomIndex];
                if (isCanApply(fid)) {
                    fams.add(fid);
                    if (fams.size() == num) { //从系统家族选指定数量
                        return fams;
                    }
                }
            }
        }
        return fams;
    }

    public static void openSysFamParty() {
        for (Entry<Long, String> famEntry : SysFamily2ChiefName.entrySet()) {
            new Procedure() {
                @Override
                protected boolean process() throws Exception {
                    xbean.Family familyInfo = xtable.Family.get(famEntry.getKey());
                    familyInfo.setLastpartyopentime(System.currentTimeMillis());
                    long mapid = FamilyId2MapId.getOrDefault(familyInfo.getFamilyid(), 0L);
                    if (mapid == 0) {
                        FMap.creatAndOpenParty(familyInfo);
                    } else {
                        FMap.sendSystemFamAutoOpenParty(mapid);
                    }
                    SFamilyPartyOpenNotify notify = new SFamilyPartyOpenNotify();
                    notify.openid = 0;
                    FFamily.multicastAllFamily(familyInfo, notify);
                    return true;
                }
            }.call();
        }
    }
}

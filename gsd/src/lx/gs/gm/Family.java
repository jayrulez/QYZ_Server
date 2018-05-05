package lx.gs.gm;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import cfg.cmd.condition.LimitType;
import cfg.ectype.EctypeType;
import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import lx.gs.family.*;
import lx.gs.family.msg.*;
import lx.gs.leaderboard.LeaderBoardModule;
import lx.gs.limit.FLimit;
import lx.gs.map.FMap;
import lx.gs.task.FTask;
import map.msg.Bonus;
import map.msg.SKillGodAnimalNotify;
import map.msg.SStartFamilyParty;
import map.msg.SingleRoleBonus;

import java.util.HashMap;

@Module(comment="家族模块GM命令")
public class Family {

	@Cmd(comment="创建家族")
	public Object create(
			   @Param(name="name", comment="家族名字")String fname){
		final long roleid = GmSession.current().getRoleid();			
		HashMap<String, Object> result = new HashMap<String,Object>();
		PCreateFamily p = new PCreateFamily(new CCreateFamily(fname));
		p.setRoleId(roleid);
		p.call();
		xbean.Family f = FFamily.getFamilyByRoleId(roleid);
		if(f != null){
			result.put("family", f);
		}
		else{
			result.put("result", "fail");
		}
		return result;
		
	}
	
	@Cmd(comment="查找家族")
	public Object find(
			@Param(name="name", comment="家族名字")String name){
		final long roleid = GmSession.current().getRoleid();
		xdb.Trace.info("Family find name=" + name);
		if(name.trim().equals("all")){
			name = "";
		}
		HashMap<String, Object> result = new HashMap<String,Object>();
		PFindFamily p = new PFindFamily(new CFindFamily(name, 1));
		p.setRoleId(roleid);
		p.call();		
		return result;
	}
	
	@Cmd(comment="查看家族名字")
	public Object getFNames(){
		HashMap<String, Object> result = new HashMap<String,Object>();
		xbean.FNameToID data = xtable.Familyname2id.get(0);
		for(java.util.Map.Entry<String, Long>e:data.getData().entrySet()){
			xdb.Trace.info("PFindFamily id={}", e.getKey());
			result.put(e.getKey(), e.getValue());
		}
		return result;
	}
	
     
    @Cmd(comment="退出家族")
    public Object quit(){
    	final long roleid = GmSession.current().getRoleid();
    	HashMap<String, Object> result = new HashMap<String,Object>();
		xbean.Family f = FFamily.getFamilyByRoleId(roleid);
		PQuitFamily p = new PQuitFamily(new CQuitFamily());
		p.setRoleId(roleid);
        p.call();
		if(f != null){
			result.put("family", f);
		}
		else{
			result.put("result", "family not existed!");
		}
		return result;
    }
    
    @Cmd(comment="查看个人家族信息")
    public Object getRoleFinfo(){
    	final long roleid = GmSession.current().getRoleid();
    	HashMap<String, Object> result = new HashMap<String,Object>();
    	xbean.RoleFamily f = FFamily.getRoleFamilyInfo(roleid);
		if(f != null){
			result.put("finfo", f);
		}
		else{
			result.put("result", "finfo not existed!");
		}
		return result;
    }
    
    @Cmd(comment="接受加入申请")
    public Object accept(@Param(name="memberid", comment="申请人id")long memberid){
    	final long roleid = GmSession.current().getRoleid();
    	HashMap<String, Object> result = new HashMap<String,Object>();
    	PAcceptRequestJoinF p = new PAcceptRequestJoinF(new CAcceptRequestJoinF(memberid));
    	p.setRoleId(roleid);
    	p.call();
    	xbean.Family f= FFamily.getFamilyByRoleId(roleid);
    	if(f != null){
			result.put("finfo", f);
		}
		else{
			result.put("result", "finfo not existed!");
		}
		return result;
    }
    
    @Cmd(comment="查看家族信息")
    public Object getFInfo(@Param(name="familyid", comment="家族id")long fid){
    	HashMap<String, Object> result = new HashMap<String,Object>();
    	xbean.Family f= FFamily.getFamilyByFamilyId(fid);
    	if(f != null){
			result.put("finfo", f);
		}
		else{
			result.put("result", "finfo not existed!");
		}
		return result;
    }
    
    @Cmd(comment="申请加入家族")
    public Object requestjoin(@Param(name="familyid", comment="家族id")long fid){
    	final long roleid = GmSession.current().getRoleid();
    	HashMap<String, Object> result = new HashMap<String,Object>();
    	PRequestJoinFamily p = new PRequestJoinFamily(new CRequestJoinFamily(fid));
    	p.setRoleId(roleid);
    	p.call();
    	xbean.Family f= FFamily.getFamilyByFamilyId(fid);
    	if(f != null){
			result.put("finfo", f);
		}
		else{
			result.put("result", "finfo not existed!");
		}
		return result;
    }
    
    @Cmd(comment="烧香")
    public Object pray(@Param(name="type", comment="烧香的类型，普通，高级还是至尊,1,2,3")int burntype){
    	final long roleid = GmSession.current().getRoleid();
    	HashMap<String, Object> result = new HashMap<String,Object>();
    	PFamilyPray p = new PFamilyPray(new CFamilyPray(burntype));
    	p.setRoleId(roleid);
    	p.call();
    	xbean.Family f= FFamily.getFamilyByRoleId(roleid);
    	if(f != null){
			result.put("finfo", f);
		}
		else{
			result.put("result", "finfo not existed!");
		}
		return result;
    }

    @Cmd(comment="增加家族资金")
    public String addFamilyMoney(@Param(name="amount", comment="增加的数量")int amount){
        final long roleid = GmSession.current().getRoleid();
        xbean.Family familyInfo = FFamily.getFamilyByRoleId(roleid);
        if(familyInfo == null){
            return "当前不在家族中";
        }

        familyInfo.setMoney(familyInfo.getMoney() + amount);
        return "当前家族资金为: " + familyInfo.getMoney();
    }

    @Cmd(comment="开启自己家族经验雨活动")
    public Object openParty(){
        final long roleid = GmSession.current().getRoleid();
        SStartFamilyParty msg = new SStartFamilyParty();
        msg.remaintime = CfgMgr.familyparty.duration * 1000;
        FMap.dispatchMessageInProcedure(roleid, msg);//开启家族聚宴,只能在家族副本内开启
        return "succ";
    }

    @Cmd(comment="开启自己家族所在家族神兽活动")
    public String openGodAnimal(){
        final long roleid = GmSession.current().getRoleid();
        xbean.Family family = FFamily.getFamilyByRoleId(roleid);
        if(family == null){
            return "当前不在家族中";
        }
//        long now = System.currentTimeMillis();
//        if(family.getBeatanimalactivity().getEndtime() != 0){
//            return "神兽活动正在进行";
//        }
//        family.getBeatanimalactivity().setStarttime(now);
//        family.getBeatanimalactivity().setEndtime(now + CfgMgr.bossconfig.battletime * 1000);
        long mapid = FFamily.FamilyId2MapId.getOrDefault(family.getFamilyid(), 0L);
        if(mapid == 0){
            FMap.creatAndSetGodAnimal(roleid, family);
        }else {
            FMap.sendGodAnimalInfoToMap(mapid, family);
        }
        return "神兽活动开启成功";
    }

    @Cmd(comment = "一键加入家族")
    public Object joinAllFamily() {
        gs.Utils.call(new CRequestJoinAllFamily());
        return "succ";
    }

    @Cmd(comment = "测试系统消息")
    public Object testSystemMsg() {
        final long roleid = GmSession.current().getRoleid();
        String rolename = xtable.Roleinfos.selectName(roleid);
        SKillGodAnimalNotify notify = new SKillGodAnimalNotify();
        Bonus fisrtBonus = new Bonus();
        fisrtBonus.items.put(10100073, 5);
        Bonus luckBonus = new Bonus();
        luckBonus.items.put(10100073, 1);
        notify.membersbonus.add(new SingleRoleBonus(roleid, rolename, fisrtBonus));
        notify.luckybonus.add(new SingleRoleBonus(roleid, rolename, luckBonus));
        notify.lasthitbonus.add(new SingleRoleBonus(roleid, rolename, luckBonus));
        gnet.link.Onlines.getInstance().broadcast(notify);
        return "succ";
    }

    @Cmd(comment = "查询角色家族信息")
    public Object queryRoleFamily(){
        final long roleid = GmSession.current().getRoleid();
        return FFamily.getRoleFamilyInfo(roleid);
    }

    @Cmd(comment = "重置参加仙府聚宴的机会")
    public Object resetjoinparty(){
        final long roleid = GmSession.current().getRoleid();
        FFamily.getRoleFamilyInfo(roleid).setHasjoinpartytoday(0);
        return "succ";
    }

    @Cmd(comment = "查询家族排名")
    public Object returnRank(){
        return LeaderBoardModule.familyRankMap;
    }

    @Cmd(comment = "查询家族信息")
    public Object queryFamilyInfo(@Param(name="familyid", comment="家族id")int familyid){
        xbean.Family familyinfo = FFamily.getFamilyByFamilyId(familyid);
        if(familyinfo == null){
            return "该家族不存在";
        }else {
            return familyinfo;
        }
    }

    @Cmd(comment = "清除某个家族的申请信息")
    public Object clearFamilyRequestInfo(@Param(name="familyid", comment="家族id")int familyid){
        xbean.Family familyinfo = FFamily.getFamilyByFamilyId(familyid);
        if(familyinfo == null){
            return "该家族不存在";
        }else{
            familyinfo.getRequestinglist().clear();
        }
        return "succ";
    }

    @Cmd(comment = "清除玩家申请某个家族的申请信息")
    public Object clearRoleRequestInfo(@Param(name="familyid", comment="家族id")int familyid){
        long roleid = GmSession.current().getRoleid();
        xbean.RoleFamily roleFamily = FFamily.getRoleFamilyInfo(roleid);
        roleFamily.getRequestedfamily().remove(familyid);
        return "succ";
    }

    @Cmd(comment = "重置限制次数")
    public Object resetlimit(){
        long roleid = GmSession.current().getRoleid();
        FLimit.setLimitTimes(roleid, ConfigId.DAILY_ECTYPE, EctypeType.CURRENCY, LimitType.DAY, 0);
        return "succ";
    }

    @Cmd(comment = "增加一个家族环任务")
    public Object addOneFamTask(@Param(name="taskid", comment = "增加的任务id") int taskid,
                                @Param(name="npcid", comment="增加的npcid") int npcid){
        long roleid = GmSession.current().getRoleid();
        xbean.RoleTask taskInfo = FTask.getTask(roleid);
        xbean.FamilyTaskDetail newTask = xbean.Pod.newFamilyTaskDetail();
        newTask.setNpcid(npcid);
        newTask.setTaskid(taskid);
        taskInfo.getAcceptedfamilytasks().add(newTask);
        return taskInfo;
    }

    @Cmd(comment = "转移系统家族族长")
    public Object tranSystemChief(){
        FFamily.sysFamTransChief();
        return "succ";
    }

}

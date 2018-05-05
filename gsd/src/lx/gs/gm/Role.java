package lx.gs.gm;

import cfg.CfgMgr;
import cfg.achievement.CounterType;
import cfg.currency.CurrencyType;
import cfg.item.EItemBindType;
import common.ErrorCode;
import gm.GmCmdResult;
import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import gnet.DeliverClient;
import gnet.GGetPayReturn;
import gnet.GGetPayReturnInfo;
import gnet.NotifyOrderInfo;
import gnet.link.Onlines;
import gs.Listeners;
import lx.gs.achievement.FAchievement;
import lx.gs.friend.FFriend;
import lx.gs.friend.FFriendModule;
import lx.gs.friend.msg.SAcceptFriendNotify;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.mail.FMail;
import lx.gs.map.FMap;
import lx.gs.marriage.FMarriage;
import lx.gs.pay.CGetAppOrder;
import lx.gs.pay.CGetPayReturn;
import lx.gs.pay.FPay;
import lx.gs.pay.PPayOrderCallback;
import lx.gs.pickcard.FPickCard;
import lx.gs.role.FRole;
import lx.gs.role.msg.CSetConfigure;
import lx.gs.role.msg.CUseCode;
import lx.gs.role.msg.SDayOver;
import lx.gs.task.FTask;
import lx.gs.task.Task;
import lx.gs.task.TaskModule;
import lx.gs.task.msg.CCancelTask;
import map.msg.SChangeVipLevel;
import xdb.Procedure;

import java.util.*;
import java.util.Map;

@Module(comment="角色模块GM命令")
public class Role {
	private GmCmdResult error(String fmt, Object... params) {
		return GmCmdResult.error(String.format(fmt, params));
	}

	@Cmd(comment="加钱或经验或各种值.")
	public Object addCurrency(@Param(name="type", comment="货币类型,见cfg.currency.CurrencyType") int type,
		@Param(name="num", comment="数目") int num) {
		final long roleid = GmSession.current().getRoleid();
		new Procedure() {
			@Override
			protected boolean process() {
				FRole.addCurrency(roleid, type, num, By.Gm);
				return true;
			}
		}.call();
		return num;
	}

    @Cmd(comment="模拟消耗货币.")
    public Object costCurrency(@Param(name="type", comment="货币类型,见cfg.currency.CurrencyType") int type,
                              @Param(name="num", comment="数目") long num){
        final long roleid = GmSession.current().getRoleid();
        xbean.RoleInfo roleInfo = xtable.Roleinfos.get(roleid);
        if(FRole.checkCostCurrency(roleid, roleInfo, type, num, By.Gm)) {
            return roleInfo.getCurrencys();
        }else {
            return "not enough currency";
        }
    }

    @Cmd(comment="直接设置货币数量.")
    public Object setCurrency(@Param(name="type", comment="货币类型,见cfg.currency.CurrencyType") int type,
                              @Param(name="num", comment="数目") long num){
        final long roleid = GmSession.current().getRoleid();
        xbean.RoleInfo roleInfo = xtable.Roleinfos.get(roleid);
        roleInfo.getCurrencys().put(type, num);
        return roleInfo.getCurrencys();
    }

	public HashMap<Integer, Integer> convert(String numbers) {
		final String[] nums = numbers.split(":");
		final HashMap<Integer, Integer> numMap = new HashMap<Integer, Integer>();
		for(int i = 0 ; i < nums.length - 1 ; i += 2) {
			numMap.put(Integer.parseInt(nums[i]), Integer.parseInt(nums[i+1]));
		}
		return numMap;
	}

	@Cmd(comment="添加运营发奖邮件")
	public Object addMail(@Param(name="mailid", comment = "邮件模板id") int mailid,
						   @Param(name="title", comment = "标题") String title,
						  @Param(name="content", comment="内容") String content,
						  @Param(name="currencys", comment="货币,格式 货币1:个数1:货币2:个数2 ...") String currencys,
						  @Param(name="items", comment ="物品,格式  itemid1:num1:item2:num2 ....") String items) {
		final long roleid = GmSession.current().getRoleid();
		final map.msg.Bonus bonus = new map.msg.Bonus();
		bonus.bindtype = EItemBindType.BOUND;
		bonus.items.putAll(convert(currencys));
		bonus.items.putAll(convert(items));
		FMail.addMail(roleid, mailid, title, content, bonus);
		return "";
	}

	@Cmd(comment="模拟角色登陆")
	public Object login(
			@Param(name="roleid", comment="角色id") long roleid){

		xbean.RoleInfo roleInfo = xtable.Roleinfos.get(roleid);
		if(roleInfo == null){
			return error("角色信息未找到. roleid = " + roleid);
		}

		GmSession.current().setRoleid(roleid);

		return "角色登陆成功：roleid = " + roleid + ", name = " + roleInfo.getName() + ", level=" + roleInfo.getLevel();
	}
	
	@Cmd(comment="模拟角色登陆,使用游戏内名称登录")
	public Object loginByName(
			@Param(name="rolename", comment="角色名称") String rolename){
		Long roleid = xtable.Rolename2ids.get(rolename);
		if(roleid == null){
			return error("角色信息未找到. rolename = " + rolename);
		}
		xbean.RoleInfo roleInfo = xtable.Roleinfos.get(roleid);
		if(roleInfo == null){
			return error("角色信息未找到. roleid = " + roleid);
		}

		GmSession.current().setRoleid(roleid);

		return "角色登陆成功：roleid = " + roleid + ", name = " + roleInfo.getName();
	}
	
	@Cmd(comment="显示在线玩家")
	public Object onlines() {
		StringBuilder sb = new StringBuilder();
		sb.append("在线玩家列表\r\n");
		for(gnet.link.Role role : Onlines.getInstance().roles()) {
			final long roleid = role.getRoleid();
			xbean.RoleInfo roleInfo = xtable.Roleinfos.select(roleid);
			sb.append("roleid=" + roleid + ", name=" + roleInfo.getName() + "\r\n");
		}
        sb.append("总在线人数为："+ Onlines.getInstance().roles().length + "\r\n");
		return sb.toString();
	}

    @Cmd(comment="查询角色信息")
    public Object queryRoleInfo(){
        long roleid = GmSession.current().getRoleid();
        return xtable.Roleinfos.select(roleid);
    }

    @Cmd(comment="查询用户信息")
    public Object queryUserInfo(){
        long roleid = GmSession.current().getRoleid();
        long userid = xtable.Roleinfos.selectUserid(roleid);
        return xtable.Users.select(userid);
    }

	@Cmd(comment="设置角色等级")
	public Object setLevel(
			@Param(name="level", comment="角色等级") int level){
		long roleid = GmSession.current().getRoleid();

		if(level < 0){
			return error("level必须大于等于0");
		}
		if(CfgMgr.exptable.get(level) == null) {
			return error("level超过最大等级:" + CfgMgr.exptable.size());
		}
		xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
		if(role == null){
			return error("角色信息未找到. roleid = " + roleid);
		}

		final int oldLevel = role.getLevel();
		if(level <= oldLevel) {
            role.setLevel(level);
            return "角色等级: " + oldLevel + " --> " + level;
			//return error("不能低于现在的等级:" + oldLevel);
		}

		for(int i = role.getLevel() ; i < level ; i++) {
			FRole.addCurrency(roleid, CurrencyType.JingYan, CfgMgr.exptable.get(i).exp, By.Gm);
		}
		return "角色等级: " + oldLevel + " --> " + level;
	}

	@Cmd(comment="设角色VIP等级")
	public Object setVipLevel(@Param(name="viplevel", comment="vip等级") int viplevel) {
		final long roleid = GmSession.current().getRoleid();
		final xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
		final int oldLevel = role.getViplevel();
		role.setViplevel(viplevel);

		FMap.dispatchMessageInProcedure(roleid, new SChangeVipLevel(viplevel));
		return "角色vip等级:" + oldLevel + "--> " + viplevel;
	}

	@Cmd(comment = "强制进行零点刷新")
	public void dayOver() {
		Listeners.INSTANCE.dayIdle();
	}

	@Cmd(comment = "忽略条件接取某任务")
	public String addTaskNocheck(@Param(name="taskid", comment = "任务id") int taskid) {
		final long roleid = GmSession.current().getRoleid();
		return FTask.gmAddTask(roleid, taskid);
	}
	
	@Cmd(comment = "增加主线任务和支线任务（需要删除当前正在做的的主线任务）")
	public String addMainTask(@Param(name="taskid", comment = "任务id") int taskid){
		final long role = GmSession.current().getRoleid();
		final xbean.RoleTask info =FTask.getTask(role);
		Task toAddTask = TaskModule.tasks.get(taskid);
		int toAddTaskType = toAddTask.getTaskCfg().basic.tasktype;
		int cancleid = 0;
		boolean findSameBranch = false;
		if(toAddTaskType == 1){//如果是主线任务
			for(int i : info.getTasks().keySet()){
				Task task = TaskModule.tasks.get(i);
				if(task.getTaskCfg().basic.tasktype == 1){
					cancleid = i;
					break;
				}
			}
		}else if (toAddTaskType == 2) {//如果是支线任务，则1,判断是否是新线；2，判断是否已经存在同一个支线
			for(int i : info.getTasks().keySet()){
				Task task = TaskModule.tasks.get(i);
				if(task.getTaskCfg().basic.tasktype == 2){//先找为支线任务的类型
					if(FTask.isSameBranchTask(i, taskid)){//如果找到了同一条支线上的
						cancleid = i;
						findSameBranch = true;
						break;
					}
				}
			}
			if(!findSameBranch){//如果是一条新的支线
				int acceptBranchs = info.getAcceptbranchtasks();
				if(acceptBranchs >= 5){
					return "同时接取支线任务条数不能超过5";
				}
				info.setAcceptbranchtasks(acceptBranchs + 1); 
			}
		}
		
		
		if(cancleid != 0){//要取消的任务id
			gs.Utils.call(new CCancelTask(cancleid));
		}
		return FTask.gmAddTask(role, taskid);
	}
	
	@Cmd(comment = "取消任务")
	public String cancelTask(@Param(name="taskid", comment = "任务id") int taskid) {
		final long roleid = GmSession.current().getRoleid();
		return FTask.checkCancelTask(FTask.getTask(roleid), taskid).getDesc();
	}
	
	@Cmd(comment="查询任务完成次数")
	public String queryTask(@Param(name="taskid", comment = "任务id") int taskid) {
		final long roleid = GmSession.current().getRoleid();
		final xbean.RoleTask info =FTask.getTask(roleid);
		xbean.TaskHistory his = info.getHistorys().get(taskid);
		int count = his == null ? 0 : his.getCount();
		return  count + "";
	}

    @Cmd(comment="查询角色任务数据")
    public Object queryRoleTask(){
        final long roleid = GmSession.current().getRoleid();
        return FTask.getTask(roleid);
    }

    @Cmd(comment="查询所有能做的支线任务")
    public Object queryAllBranches(){
        final long roleid = GmSession.current().getRoleid();
        final xbean.RoleTask info =FTask.getTask(roleid);
        return  info.getAllcandobranch();
    }

    @Cmd(comment="测试用，增加一个支线任务")
    public Object addOneBranch(@Param(name="taskid", comment = "任务id") int taskid){
        final long roleid = GmSession.current().getRoleid();
        final xbean.RoleTask info =FTask.getTask(roleid);
        info.getAllcandobranch().add(taskid);
        return "succ";
    }

    @Cmd(comment="查询任务历史完成记录")
    public Object queryAllTask() {
        final long roleid = GmSession.current().getRoleid();
        final xbean.RoleTask info =FTask.getTask(roleid);
        return  info.getHistorys();
    }


	@Cmd(comment = "清除每日完成次数")
	public Object resetDailyLimit() {
		FLimit.resetLimitAndCoolDown(GmSession.current().getRoleid());
        return "succ";
	}

	@Cmd(comment="查询任务数据")
	public String queryData(@Param(name="taskid", comment = "任务id") int taskid) {
		final long roleid = GmSession.current().getRoleid();
		final xbean.RoleTask info =FTask.getTask(roleid);
		xbean.TaskData data = info.getTasks().get(taskid);
		if(data == null){
			return "没有接取当前任务";
		}
		return data.getCounter().toString();
	}

    @Cmd(comment="清除求婚信息")
    public String clearProposeInfo(){
        final long roleid = GmSession.current().getRoleid();
        xbean.RoleMarriage marriageInfo = FMarriage.getMarriageInfo(roleid);
        marriageInfo.setCurproposeid(0);
        marriageInfo.setStartproposetime(0);
        return "OK";
    }

    @Cmd(comment="查询婚姻情况")
    public String roleProposeInfo(){
        final long roleid = GmSession.current().getRoleid();
        xbean.RoleMarriage marriageInfo = FMarriage.getMarriageInfo(roleid);
        return "伴侣id： " + marriageInfo.getCoupleroleid() + " 当前求婚对象id：" +  marriageInfo.getCurproposeid();
    }

    @Cmd(comment="跳过新手指引")
    public String skipNoviceGuide() {
        final long roleid = GmSession.current().getRoleid();
        gs.Utils.call(new CSetConfigure("clearnoviceguide", "return {}"));
        return "succ";
    }

    @Cmd(comment="查询抽卡次数")
    public String queryPickCard(){
        final long roleid = GmSession.current().getRoleid();
        xbean.PickCardInfo info = FPickCard.get(roleid);
        StringBuilder sb = new StringBuilder();
        sb.append("huobanhighyuanbao ").append(info.getHuobanhighyuanbao() + " ")
        .append("huobanlowyuanbao ").append(info.getHuobanlowyuanbao() + " ")
        .append("fabaoyuanbao ").append(info.getFabaoyuanbao() + " ")
        .append("fabaofree ").append(info.getFabaofree() + " ")
        .append("fabaoxunibi").append(info.getFabaoxunibi());
        return sb.toString();
    }

    @Cmd(comment="角色信息复制，不包括社交关系, 任务用roleaddmaintask单独添加，默认复制双方的门派和性别相同, 否则复制失败")
    public String roleInfoCopy(@Param(name="from", comment = "从哪转") long fromroleid, @Param(name="to", comment = "到哪去") long toroleid){
        new Procedure() {
            @Override
            protected boolean process() {
                //RoleInfo
                xbean.RoleInfo toRoleInfo = xtable.Roleinfos.get(toroleid);
                long touserid = toRoleInfo.getUserid();
                String toRoleName = toRoleInfo.getName();
                xbean.RoleInfo fromRoleInfo = xtable.Roleinfos.get(fromroleid);
                if(toRoleInfo.getProfession() != fromRoleInfo.getProfession() || toRoleInfo.getGender() != fromRoleInfo.getGender()){
                    return false;
                }
                xbean.RoleInfo newRoleInfo = fromRoleInfo.copy();
                newRoleInfo.setUserid(touserid);
                newRoleInfo.setName(toRoleName);
                xtable.Roleinfos.getTable().put(toroleid, newRoleInfo);

                //RoleAttrs
                xbean.RoleAttr fromRoleAttr = xtable.Roleattrs.get(fromroleid);
                xbean.RoleAttr newRoleAttr = fromRoleAttr.copy();
                newRoleAttr.setRoleid(toroleid);
                xtable.Roleattrs.getTable().put(toroleid, newRoleAttr);
                //RoleEctype
                xbean.RoleEctype fromEctype = xtable.Roleectypes.get(fromroleid);
                xbean.RoleEctype newEctype = fromEctype.copy();
                xtable.Roleectypes.getTable().put(toroleid, newEctype);
                //ectypesingle
                //roleitembag
                xbean.RoleItemBag newItemBag = xtable.Roleitembag.get(fromroleid).copy();
                xtable.Roleitembag.getTable().put(toroleid, newItemBag);
                //roleequipbag
                xbean.RoleEquipBag newEquipBag = xtable.Roleequipbag.get(fromroleid).copy();
                xtable.Roleequipbag.getTable().put(toroleid, newEquipBag);
                //rolefragmentbag
                xbean.RoleFragmentBag newFragmentBag = xtable.Rolefragmentbag.get(fromroleid).copy();
                xtable.Rolefragmentbag.getTable().put(toroleid, newFragmentBag);
                //roletalismanbag
                xbean.RoleTalismanBag newTalismanBag = xtable.Roletalismanbag.get(fromroleid).copy();
                xtable.Roletalismanbag.getTable().put(toroleid, newTalismanBag);
                //amulet
                xbean.RoleAmuletInfo newAmuletInfo = xtable.Amulet.get(fromroleid).copy();
                xtable.Amulet.getTable().put(toroleid, newAmuletInfo);
                //jade
                xbean.RoleJadeInfo newJadeInfo = xtable.Jade.get(fromroleid).copy();
                xtable.Jade.getTable().put(toroleid, newJadeInfo);
                //rolepet
                xbean.RolePet newRolePet = xtable.Rolepet.get(fromroleid).copy();
                xtable.Rolepet.getTable().put(toroleid, newRolePet);
                //roleequipdepot
                xbean.RoleEquipDepot newEquipDepot = xtable.Roleequipdepot.get(fromroleid).copy();
                xtable.Roleequipdepot.getTable().put(toroleid, newEquipDepot);
                //roleitemdepot
                xbean.RoleItemDepot newItemDepot = xtable.Roleitemdepot.get(fromroleid).copy();
                xtable.Roleitemdepot.getTable().put(toroleid, newItemDepot);
                //rolefragmentdepot
                xbean.RoleFragmentDepot newFragDepot = xtable.Rolefragmentdepot.get(fromroleid).copy();
                xtable.Rolefragmentdepot.getTable().put(toroleid, newFragDepot);
                //roletalismandepot
                xbean.RoleTalismanDepot newTalismanDepot = xtable.Roletalismandepot.get(fromroleid).copy();
                xtable.Roletalismandepot.getTable().put(toroleid, newTalismanDepot);
                //goldcoindepot
                long coidDepot = xtable.Goldcoindepot.get(fromroleid);
                xtable.Goldcoindepot.getTable().put(toroleid, coidDepot);
                //dress
                xbean.RoleDress newRoleDress = xtable.Dress.get(fromroleid).copy();
                xtable.Dress.getTable().put(toroleid, newRoleDress);
                //ride
                xbean.RoleRide newRide = xtable.Ride.get(fromroleid).copy();
                xtable.Ride.getTable().put(toroleid, newRide);
                //roletask? 单独添加
                //roleskill
                xbean.RoleSkill newSkill = xtable.Roleskill.get(fromroleid).copy();
                xtable.Roleskill.getTable().put(toroleid, newSkill);
                //RoleTitle
                xbean.RoleTitle newTitle = xtable.Title.get(fromroleid).copy();
                xtable.Title.getTable().put(toroleid, newTitle);
                return true;
            }
        }.call();
        return "Copy Over!";
    }

    @Cmd(comment = "模拟支付")
    public Object pay(@Param(name = "platorderid", comment = "platorderid")String platorderid,
                      @Param(name="gsorderid", comment = "gsorderid")String gsorderid,
                      @Param(name="productid", comment = "商品id(cfg.pay.charge)")int productid) {
        final long roleid = GmSession.current().getRoleid();
        PPayOrderCallback.doneOrder(roleid, xtable.Roleinfos.get(roleid), FPay.getRolePay(roleid), platorderid, gsorderid, CfgMgr.charge.get(productid), 0);
        return "succ";
    }

    @Cmd(comment = "使用礼包码")
    public Object useCode(@Param(name="activecode", comment = "礼包码id")String code) {
        gs.Utils.call(new CUseCode(code));
        return "succ";
    }

    @Cmd(comment = "获取玩家充值记录")
    public Object getPayStatus(){
        final long roleid = GmSession.current().getRoleid();
        xbean.RolePay payStatus = FPay.getRolePay(roleid);
        return payStatus;
    }

    @Cmd(comment = "生成一个订单号")
    public Object getAppOrder(@Param(name="productid", comment = "商品id")int productid){
        gs.Utils.call(new CGetAppOrder(productid));
        return "succ";
    }

    @Cmd(comment = "模拟支付成功，从au发来的通知")
    public Object paySucceddNotify(@Param(name="orderid", comment = "订单id")String gsorderid,
                                   @Param(name="amount", comment = "充值数量")String amountnum){
        final long roleid = GmSession.current().getRoleid();
        xbean.RoleInfo info = xtable.Roleinfos.get(roleid);
        NotifyOrderInfo notify = new NotifyOrderInfo();
        notify.gsorderid = gsorderid;
        notify.userid = info.getUserid();
        java.util.Map<String, String> datas = new HashMap<>();
        datas.put("amount",amountnum);
        datas.put("bookAmount",amountnum);
        datas.put("agentId", "-1218");
        notify.vars = gnet.PlatUtils.marshalVars(datas);
        gs.Utils.call(notify);
        return "succ";
    }

    @Cmd(comment = "查询一个玩家的所有未支付的订单, gsorderid - productid")
    public Object queryAppOrder(){
        final long roleid = GmSession.current().getRoleid();
        Map<Long, Integer> result = new HashMap<>();
        xtable.Processingorders.getTable().walk((id, orderinfo) -> {
            if(orderinfo.getRoleid() ==  roleid){
                result.put(id, orderinfo.getProductid());
            }
            return true;
        });
        return result;
    }

    @Cmd(comment = "查询一个玩家所有支付成功的订单")
    public Object querySuccessOrder(){
        final long roleid = GmSession.current().getRoleid();
        xbean.RoleOrderHistroy orderhistory = xtable.Roleorderhistorys.get(roleid);
        if(orderhistory == null){
            orderhistory = xbean.Pod.newRoleOrderHistroy();
            xtable.Roleorderhistorys.insert(roleid, orderhistory);
        }
        return orderhistory.getSucceedorder();
    }

    @Cmd(comment = "直接添加一个角色为好友")
    public Object addFriend(@Param(name="otherroleid", comment = "要加的好友id")long otherroleid){
        final long roleid = GmSession.current().getRoleid();
        xbean.RoleFriendsInfo myinfo = FFriend.getRoleFriendsInfo(roleid);
        xbean.RoleFriendsInfo toinfo = FFriend.getRoleFriendsInfo(otherroleid);
        if (null == myinfo || null == toinfo) {
            return ErrorCode.FRIEND_NOT_FOUND;
        }
        if (myinfo.getFriends().size() >= FFriendModule.MAX_FRIENDLIST) {
            return ErrorCode.FRIEND_MY_FRIENDLIST_OVERFLOW;
        }
        if (toinfo.getFriends().size() >= FFriendModule.MAX_FRIENDLIST) {
            return ErrorCode.FRIEND_PEER_FRIENDLIST_OVERFLOW;
        }
        if (toinfo.getFriends().containsKey(roleid) || myinfo.getFriends().containsKey(otherroleid)) {
            return ErrorCode.FRIEND_ALREADY_FRIEND;
        }
        myinfo.getRequesting().remove(otherroleid);
        myinfo.getBlacklist().remove(otherroleid);
        toinfo.getRequesting().remove(roleid);
        toinfo.getBlacklist().remove(roleid);

        long now = System.currentTimeMillis();
        xbean.RoleFriend newfriend = xbean.Pod.newRoleFriend();
        newfriend.setFrienddegress(0);
        newfriend.setRelation(0);
        newfriend.setTime(now);
        myinfo.getFriends().put(otherroleid, newfriend);

        xbean.RoleFriend tofriend = xbean.Pod.newRoleFriend();
        tofriend.setFrienddegress(0);
        tofriend.setRelation(0);
        tofriend.setTime(now);
        toinfo.getFriends().put(roleid, tofriend);

        lx.gs.friend.msg.SAcceptFriend result = new lx.gs.friend.msg.SAcceptFriend();
        result.friendlist.add(FFriend.makeProtocolBasicFriendInfo(otherroleid, newfriend));

        xdb.Transaction.tsendWhileCommit(roleid, result);

        FAchievement.updateCounter(otherroleid, CounterType.FRIEND_NUM, toinfo.getFriends().size());

        FAchievement.updateCounter(roleid, CounterType.FRIEND_NUM, myinfo.getFriends().size());
        return "succ";
    }

    @Cmd(comment = "客户端解锁所有模块，服务器限制还是存在")
    public Object clientUnlock(){
        final long roleid = GmSession.current().getRoleid();
        final xbean.RoleConfigure conf = FRole.getConfigure(roleid);
        conf.getDatas().put("unlockallmodules", "1");
        return "succ";
    }

    @Cmd(comment = "服务器解锁所有模块，其实是给任务次数加上1")
    public Object ServerUnlock(){
        final long roleid = GmSession.current().getRoleid();
        xbean.RoleTask roleTask = FTask.getTask(roleid);
        CfgMgr.moduleunlockcond.values().forEach(m -> {
            if(m.opentaskid > 0){
                FTask.addCompleteCount(roleTask, m.opentaskid);
            }
        });
        return "succ";
    }

    @Cmd(comment = "给客户端发SDayOver")
    public Object sendDayOver(){
        final long roleid = GmSession.current().getRoleid();
        gnet.link.Onlines.getInstance().send(roleid, new SDayOver());
        return "succ";
    }

    @Cmd(comment = "领取充值补偿")
    public Object getPayReturn() {
        gs.Utils.call(new CGetPayReturn());
        return "succ";
    }

    @Cmd(comment = "重置充值补偿领取记录")
    public Object resetPayReturn() {
        final long roleid = GmSession.current().getRoleid();
        FPay.getRolePay(roleid).setHasgotpayreturn(false);
        DeliverClient.getInstance().sendByGsToAuany(xtable.Roleinfos.selectUserid(roleid), roleid, new GGetPayReturnInfo());
        return "succ";
    }

    @Cmd(comment = "查询玩家好友信息")
    public Object queryfriendinfo(){
        final long roleid = GmSession.current().getRoleid();
        return FFriend.getRoleFriendsInfo(roleid);
    }
}

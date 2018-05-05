package lx.gs.login;

import cfg.CfgMgr;
import gs.Listeners;
import lx.gs.bonus.FBonus;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.family.FFamily;
import lx.gs.logger.FLogger;
import lx.gs.pay.FPay;
import lx.gs.role.FRole;
import lx.gs.role.FRoleAttr;
import lx.gs.task.FTask;
import xdb.Transaction;

import java.util.TimeZone;

public class PRoleLogin extends xdb.Procedure {
	private long userid;
	private long roleid;
	private lx.gs.login.CRoleLogin pFromLink;
	private lx.gs.login.SRoleLogin result;

	public PRoleLogin(long userid, long roleid, lx.gs.login.CRoleLogin pFromLink) {
		this.userid = userid;
		this.roleid = roleid;
		this.pFromLink = pFromLink;
		this.result = new lx.gs.login.SRoleLogin();
	}

	private void sendResult() {
		gnet.link.Onlines.getInstance().sendResponse(pFromLink, result);
	}

	@Override
	protected boolean process() throws Exception {
        final xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
        Transaction.texecuteWhileCommit(new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.User user = xtable.Users.get(userid);
                user.setLastloginrole(roleid);
                return true;
            }
        });

		final long now = System.currentTimeMillis();
		xbean.RoleWelfareInfo welfare = FBonus.get(roleid);
		if(!common.Time.isSameDay(role.getLastaddlogindaytime(), now)) {//如果是当天第一次登录
			role.setTotaldayonlinetime(0);
            role.setLastaddlogindaytime(now);
			role.setLogindaycount(role.getLogindaycount() + 1);
//			if (role.getLastlogintime() < common.Time.getTimeInfo().monthZeroTime) { // 登录时，每月需要判断清空一次签到日期,清空连续签到,清空补签
//				welfare.getSigndate().clear();
//				welfare.setAddsigncount(0);
//			}
            if(welfare.getSigndate().size() >= 30){//现在是签到30天一个循环
                welfare.getSigndate().clear();
            }
            welfare.setHastodaysign(0);
			if (!common.Time.isContinuesDay(role.getLastlogouttime(), now)) {// 如果不是连续登录，那么连续登录天数重置
				role.setContinuesloginday(1);
				role.setLeftgifttimes(1);
			} else {// 如果是每天连续登录
				role.setContinuesloginday(role.getContinuesloginday() + 1); // 连续登录天数加一天,最多记录连续7天登录
				role.setLeftgifttimes(Math.min(7 , role.getContinuesloginday()));// 最多有7次连续登录礼包
			}
            welfare.getReceiveconlogingift().clear();
			FLogin.onContinueLoginDayChange(roleid, role.getContinuesloginday());
			int lastMonthcardTimes = role.getMonthcardlefttimes();
			if (role.getMonthcardlefttimes() > 0) {
				int a = role.getMonthcardlefttimes() - (int) common.Time.intervalDays(role.getLastlogouttime(), now);
				role.setMonthcardlefttimes(a > 0 ? a : 0);// 月卡剩余天数减上次登录天数和现在的间隔
			}
			if (role.getMonthcardlefttimes() / 30 != lastMonthcardTimes / 30
					|| role.getMonthcardlefttimes() % 30 == 0) {
				welfare.getReceivedmonthcarddate().clear(); // 月卡30天为一个周期，每过一个30清空一次领奖情况
			}
			welfare.getReceivedonlinegift().clear(); // 清空领取的在线时长奖励类型
			welfare.setWishtimes(0); // 清空已许愿次数
            welfare.setIseatlunch(0);
            welfare.setIseatdinner(0);
            role.setLeftreporttime(CfgMgr.roleconfig.everydayreportnum);
            FDailyActivity.clearActiveInfo(roleid);
            xbean.RoleTask taskInfo = FTask.getTask(roleid);
            FTask.clearFamilyTaskInfo(taskInfo);
            if(!common.Time.isSameWeek(role.getLastlogouttime(), now)){//如果是跨周了
                taskInfo.setWeekcompletedsmallfamtasks(0);
                taskInfo.getWeekspebonus().clear();//周特殊奖励重置
            }
            xbean.RoleFamily roleFamily = FFamily.getRoleFamilyInfo(roleid);
            FFamily.resetFamilyParty(roleFamily);//重置今天参加家族仙府聚宴的机会
		}
		role.setLastlogintime(now);

		FPay.getRolePay(roleid);

		final xbean.RoleAttr roleAttr = FRoleAttr.get(roleid);
		roleAttr.getGroupattrs().clear();
        roleAttr.setRolecombatpower(0);  //客户端特殊处理，让第一次战力必然变化

		Listeners.INSTANCE.updateRoleAttr(roleid);
		FRoleAttr.updateRoleCombatPower(roleAttr);

		result.err = lx.gs.login.SRoleLogin.OK;
		FRole.genRoleDetail(roleid, role, roleAttr, result.roledetail);

		result.servertime = System.currentTimeMillis();
		result.servertimezone = TimeZone.getDefault().getID();
		result.isservermerge = 0;
		sendResult();

		FLogger.rolelogin(roleid, role);
        LoginTraceMgr.onLogin(roleid);

		gs.Listeners.INSTANCE.afterRoleLogin(roleid);
		return true;
	}
}

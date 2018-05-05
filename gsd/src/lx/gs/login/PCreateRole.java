package lx.gs.login;

import cfg.CfgMgr;
import cfg.currency.CurrencyType;
import common.ErrorCode;
import gs.Listeners;
import lx.gs.achievement.AchievementModule;
import lx.gs.logger.FLogger;
import lx.gs.role.FRole;

import java.util.Map;

public class PCreateRole extends xdb.Procedure {
	
	private long userid;
	private lx.gs.login.CCreateRole msg;
	private lx.gs.login.SCreateRole re;

	public PCreateRole(long userid, lx.gs.login.CCreateRole param) {
		this.userid = userid;
		this.msg = param;
		this.re = new lx.gs.login.SCreateRole();
		this.re.err = ErrorCode.OTHER_ERROR.getErrorId();
	}

	lx.gs.login.SCreateRole getResult() {
		return re;
	}

	@Override
	protected boolean process() throws Exception {
		//职业合法性验证
		cfg.role.Profession profession = CfgMgr.profession.get(msg.profession);
		if(profession == null || !profession.isopen){
			re.err = ErrorCode.PROFESSION_NOT_OPEN.getErrorId();
			return false;
		}
		if(msg.gender != cfg.role.GenderType.MALE && msg.gender != cfg.role.GenderType.FEMALE) {
			re.err = ErrorCode.GENDER_INVALID.getErrorId();
			return false;
		}
        String name = msg.name.trim();
		if (name.length() < CfgMgr.roleconfig.minnamelength) {
			re.err = ErrorCode.NAME_SHORTLEN.getErrorId();
			return false;
		}

		if (name.length() > CfgMgr.roleconfig.maxnamelength) {
			re.err = ErrorCode.NAME_OVERLEN.getErrorId();
			return false;
		}

		if(FRole.containsWhiteSpaceChar(name)) {
		    re.err = ErrorCode.NAME_INVALID.getErrorId();
            return false;
        }

		// 敏感词和唯一名检测
		if (FRole.isSenseWord(name)) {
			re.err = ErrorCode.NAME_SENSE.getErrorId();
			return false;
		}

		final int serverid = gs.Utils.getServerId();
		final String fullName = FLogin.makeFullName(name, serverid);
		if(xtable.Rolename2ids.get(fullName) != null) {
			re.err = ErrorCode.NAME_DUPLICATED.getErrorId();
			return false;
		}

		xbean.User user = xtable.Users.get(userid);
		if (null == user) {
			// 账号第一次创建角色时创建账号表的记录
			user = xbean.Pod.newUser();
			xtable.Users.insert(userid, user);
//			FLogger.createaccount(userid);
		}


		int sum = user.getRoleids().size();
		long nowTime = System.currentTimeMillis();
		for (Map.Entry<Long, Long> entry : user.getDeleteinfo().entrySet()) {
			if(nowTime <= entry.getValue() + LoginModule.DELETE_ROLE_PUT_OFF){
				sum++;
			}
		}
		if(sum >= 4){
			return false;
		}

		xbean.RoleInfo roleinfo = xbean.Pod.newRoleInfo();
		roleinfo.setUserid(userid);
		roleinfo.setServerid(serverid);
		roleinfo.setProfession(msg.profession);
		roleinfo.setName(name);
		roleinfo.setGender(msg.gender);
		roleinfo.setLevel(CfgMgr.roleconfig.createrolelevel);


		roleinfo.getCurrencys().put(CurrencyType.YuanBao, (long)CfgMgr.roleconfig.createroleyuanbao);
        roleinfo.getCurrencys().put(CurrencyType.BindYuanBao, (long)CfgMgr.roleconfig.createrolebindyuanbao);
		roleinfo.getCurrencys().put(CurrencyType.XuNiBi, (long)CfgMgr.roleconfig.createrolexnb);

		final long now = System.currentTimeMillis();
		roleinfo.setCreatetime(now);
//		roleinfo.setLastlogintime(now);
		roleinfo.setTotalonlinetime(0);
        roleinfo.setLastlvluptime(now);

		Long roleid = xtable.Roleinfos.insert(roleinfo);
		xtable.Rolename2ids.insert(fullName, roleid);
		user.getRoleids().add(roleid);

		// 角色创建时其他模块的初始化
		Listeners.INSTANCE.roleCreate(roleid);

		// 这两个模块有顺序要求,只好这样子了
		AchievementModule.INSTANCE.onRoleCreateInProcedure(roleid, roleinfo);
		
		FLogger.createrole(userid, roleid);

		final xbean.RoleAttr roleAttr = xbean.Pod.newRoleAttr();
		roleAttr.setRoleid(roleid);
		xtable.Roleattrs.insert(roleid, roleAttr);

		// 成功创建返回信息，发送在CCreateRole里面发送。
		re.err = ErrorCode.OK.getErrorId();
        re.servertime = System.currentTimeMillis();
		FRole.genRoleBrief(roleid, roleinfo, re.newinfo);
		
		return true;
	}
}

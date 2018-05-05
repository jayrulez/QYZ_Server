package lx.gs.login;

import lx.gs.role.FRole;

import java.util.ArrayList;
import java.util.List;

public class PGetRoleList extends xdb.Procedure {
	private long userid;
	private lx.gs.login.CGetRoleList pFromLink;
	private lx.gs.login.SGetRoleList result;

	public PGetRoleList(long userid, lx.gs.login.CGetRoleList pFromLink) {
		this.userid = userid;
		this.pFromLink = pFromLink;
		this.result = new lx.gs.login.SGetRoleList();
	}

	private void sendResult() {
		// 还没有登陆，只能使用sendResponse.
		gnet.link.Onlines.getInstance().sendResponse(pFromLink, result);
	}

	@Override
	protected boolean process() throws Exception {
		xbean.User user = xtable.Users.select(userid);
		if (null == user) {
			sendResult(); // 空结果
			return false;
		}

		result.prevloginroleid = user.getLastloginrole();
		List<Long> list = new ArrayList<>(user.getRoleids());
		long now = System.currentTimeMillis();
		user.getDeleteinfo().forEach((aLong, aLong2) -> {
			if(now <= aLong2 + LoginModule.DELETE_ROLE_PUT_OFF){
				list.add(aLong);
			}
		});
		for (long roleid : list) {
			xbean.RoleInfo roleInfo = xtable.Roleinfos.select(roleid);
			lx.gs.role.msg.RoleBrief roleBrief = new lx.gs.role.msg.RoleBrief();
			FRole.genRoleBrief(roleid, roleInfo, roleBrief);
			result.roles.add(roleBrief);
		}
		result.deleteinfo.putAll(user.getDeleteinfo());
		sendResult();
		return true;
	}
}

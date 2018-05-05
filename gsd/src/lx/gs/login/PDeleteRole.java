package lx.gs.login;

import gnet.link.Onlines;
import gs.Listeners;

public class PDeleteRole extends xdb.Procedure {
	private long userid;
	private lx.gs.login.CDeleteRole pFromLink;
	private lx.gs.login.SDeleteRole result;

	public PDeleteRole(long userid, lx.gs.login.CDeleteRole pFromLink) {
		this.userid = userid;
		this.pFromLink = pFromLink;
		this.result = new lx.gs.login.SDeleteRole();
		this.result.roleid = pFromLink.roleid;
	}

	private void sendResult(int err) {
		result.err = err;
		gnet.link.Onlines.getInstance().sendResponse(pFromLink, result);
	}

	@Override
	protected boolean process() throws Exception {
		xbean.User user = xtable.Users.get(userid);
		if (null == user) {
			sendResult(SDeleteRole.ERR);
			return false;
		}
		if(user.getDeletedroles() >= 1000){
			sendResult(SDeleteRole.DELETE_TOO_MANY_ROLE);
			return false;
		}

		if(Onlines.getInstance().find(pFromLink.roleid) != null)
            new PRoleLogout(pFromLink.roleid).call();

		user.setDeletedroles(user.getDeletedroles() + 1);
		user.getDeleteinfo().put(pFromLink.roleid, System.currentTimeMillis());
		
		// 删除角色仅仅从user表中删除引用，其他数据全部保留。如果有其他需求时再来修改这里。
		if (user.getRoleids().remove(this.pFromLink.roleid)) {
			Listeners.INSTANCE.roleDelete(this.pFromLink.roleid);
		}
		
		sendResult(SDeleteRole.OK);
		return true;
	}
}

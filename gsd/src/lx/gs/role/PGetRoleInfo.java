package lx.gs.role;

import gs.AProcedure;
import lx.gs.role.msg.CGetRoleInfo;
import lx.gs.role.msg.RoleShowInfo5;
import lx.gs.role.msg.SGetRoleInfo;

public class PGetRoleInfo extends AProcedure<CGetRoleInfo> {

	public PGetRoleInfo(CGetRoleInfo param) {
		super(param);
	}

	@Override
	protected boolean doProcess() throws Exception {
		response(new SGetRoleInfo(roleid, FRole.genRoleShowInfo(param.roleid, new RoleShowInfo5())));
		return true;
	}

}

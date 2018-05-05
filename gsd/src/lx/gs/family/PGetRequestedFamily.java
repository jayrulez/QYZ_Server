package lx.gs.family;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.family.msg.CGetRequestedFamily;
import lx.gs.family.msg.SGetRequestedFamily;

public class PGetRequestedFamily extends AProcedure<CGetRequestedFamily> {

	public PGetRequestedFamily(CGetRequestedFamily p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		SGetRequestedFamily result = new SGetRequestedFamily();
		xbean.RoleFamily info = FFamily.getRoleFamilyInfo(roleid);
        result.ids.addAll(info.getRequestedfamily().keySet());
		response(result);		
		return true;
	}

}

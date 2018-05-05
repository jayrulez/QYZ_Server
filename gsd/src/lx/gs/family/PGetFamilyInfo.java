package lx.gs.family;

import gs.AProcedure;
import lx.gs.family.msg.CGetFamilyInfo;

public class PGetFamilyInfo extends AProcedure<CGetFamilyInfo> {

	public PGetFamilyInfo(CGetFamilyInfo p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		FFamily.sendFamilyInfo(roleid);		
    	return true;
	}

}

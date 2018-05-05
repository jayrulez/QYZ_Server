package lx.gs.family;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.family.msg.CGetFamilyActivityInfo;
import lx.gs.family.msg.SGetFamilyActivityInfo;

public class PGetFamilyActivityInfo extends AProcedure<CGetFamilyActivityInfo> {

	public PGetFamilyActivityInfo(CGetFamilyActivityInfo p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		xbean.Family f = FFamily.getFamilyByRoleId(roleid);
		lx.gs.family.msg.FamilyActivity newa = new lx.gs.family.msg.FamilyActivity();
		for(java.util.Map.Entry<Integer, xbean.FamilyGodAnimal> g:f.getActivity().getGodanimalinfo().entrySet()){
			newa.godanimalinfo.put(g.getKey(), FFamily.makeProtocolFamilyAnimal(g.getValue()));
		}
		response(new SGetFamilyActivityInfo(newa));
		return true;
	}

}

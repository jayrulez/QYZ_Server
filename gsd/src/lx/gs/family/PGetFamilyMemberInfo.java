package lx.gs.family;

import xdb.Lockeys;
import gs.AProcedure;
import lx.gs.family.msg.CGetFamilyMemberInfo;
import lx.gs.family.msg.SGetFamilyMemberInfo;
import lx.gs.role.FRole;

public class PGetFamilyMemberInfo extends AProcedure<CGetFamilyMemberInfo> {

	public PGetFamilyMemberInfo(CGetFamilyMemberInfo p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		
		SGetFamilyMemberInfo result = new SGetFamilyMemberInfo();		
		result.memberid = param.memberid;
		
		xbean.Family family = FFamily.getFamilyByRoleId(param.memberid);
		xbean.FamilyMember minfo = family.getMembers().get(param.memberid);
		result.baseinfo = FFamily.makeProtocolFamilyMember(param.memberid, minfo);
		FRole.genRoleShowInfo(param.memberid, result.publicinfo);
		response(result);
		return true;
	}

}

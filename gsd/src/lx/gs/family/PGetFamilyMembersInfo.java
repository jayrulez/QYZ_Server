package lx.gs.family;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.family.msg.CGetFamilyMembersInfo;
import lx.gs.family.msg.SGetFamilyMembersInfo;

import java.util.ArrayList;
import java.util.List;

public class PGetFamilyMembersInfo extends AProcedure<CGetFamilyMembersInfo> {

	public PGetFamilyMembersInfo(CGetFamilyMembersInfo p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		SGetFamilyMembersInfo result = new SGetFamilyMembersInfo();
		xbean.Family f = FFamily.selectFamilyByRoleId(roleid);
		for(java.util.Map.Entry<Long, xbean.FamilyMember> e : f.getMembers().entrySet()){
			result.members.put(e.getKey(), FFamily.makeProtocolFamilyMember(e.getKey(), e.getValue()));
		}
		//job info
		for(java.util.Map.Entry<Integer, xbean.FamilyJobStaffList> j : f.getJobinfo().entrySet()){
			xbean.FamilyJobStaffList staffs = j.getValue();
			lx.gs.family.msg.FamilyJobStaffList newlist = new lx.gs.family.msg.FamilyJobStaffList();
			newlist.staffs.addAll(staffs.getStaffs().keySet());
			result.jobinfo.put(j.getKey(), newlist);
		}
		response(result);
		return true;
	}

}

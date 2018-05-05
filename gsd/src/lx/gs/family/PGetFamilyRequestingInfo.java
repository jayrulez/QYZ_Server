package lx.gs.family;

import cfg.family.FamilyJobEnum;
import gs.AProcedure;
import lx.gs.family.msg.CGetFamilyRequestingInfo;
import lx.gs.family.msg.SGetFamilyRequestingInfo;
import lx.gs.gm.Family;
import lx.gs.gm.Role;
import lx.gs.role.FRole;
import lx.gs.role.msg.RoleShowInfo1;
import lx.gs.role.msg.RoleShowInfo4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PGetFamilyRequestingInfo extends
		AProcedure<CGetFamilyRequestingInfo> {

	public PGetFamilyRequestingInfo(CGetFamilyRequestingInfo p) {
		super(p);
	}

	private int jobid;
    private List<Long> members = new ArrayList<>();
	@Override
	protected boolean doProcess() throws Exception {
		SGetFamilyRequestingInfo result = new SGetFamilyRequestingInfo();
		final long familyid = xtable.Rolefamily.selectCurrentfid(roleid);
        xtable.Family.getTable().select(familyid, family -> {
            this.jobid = family.getMembers().get(roleid).getFamilyjob();
            if(this.jobid > FamilyJobEnum.MEMBER)
                this.members.addAll(family.getRequestinglist().keySet());
            return family;
        });

        for(long mid : members){
            result.requestinglist.put(mid, FRole.genRoleShowInfo(mid, new RoleShowInfo4()));
        }
		response(result);
		return true;
	}

}

package lx.gs.family;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.family.msg.CCancelRequestJoinF;
import lx.gs.family.msg.SCancelRequestJoinF;
import lx.gs.family.msg.SCancelRequestJoinFNotify;
import xdb.Lockeys;

public class PCancelRequestJoinF extends AProcedure<CCancelRequestJoinF> {

	public PCancelRequestJoinF(CCancelRequestJoinF p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		final long familyid = param.familyid;
		SCancelRequestJoinF result = new SCancelRequestJoinF();
		result.familyid = familyid;

		xbean.RoleFamily info = FFamily.getRoleFamilyInfo(roleid);		
		if(info.getRequestedfamily().remove(familyid) != null){
            xbean.Family family = FFamily.getFamilyByFamilyId(familyid);
            if(family != null && family.getRequestinglist().remove(roleid) != null) {
                SCancelRequestJoinFNotify notify = new SCancelRequestJoinFNotify();
                notify.roleid = roleid;
                FFamily.sendNotifyToChiefViceChief(family, notify);
            }
		}
		response(result);
		return true;
	}

}

package lx.gs.family;

import xdb.Lockeys;
import gs.AProcedure;
import common.ErrorCode;
import lx.gs.family.msg.CRejectRequestJoinF;
import lx.gs.family.msg.SRejectRequestJoinF;
import lx.gs.family.msg.SRejectRequestJoinFNotify;

public class PRejectRequestJoinF extends AProcedure<CRejectRequestJoinF> {

	public PRejectRequestJoinF(CRejectRequestJoinF p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
	    final long memberid = param.memberid;
		lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid, memberid));
		
		SRejectRequestJoinF result = new SRejectRequestJoinF();
		result.memberid = memberid;

		xbean.Family family = FFamily.getFamilyByRoleId(roleid);
		xbean.RoleFamily info = FFamily.getRoleFamilyInfo(memberid);
		
		xbean.FamilyMember opfm = family.getMembers().get(roleid);
		cfg.family.FamilyJob job = cfg.CfgMgr.familyjob.get(opfm.getFamilyjob());
		if(!job.enrollperm){
			return error(ErrorCode.FAMILY_OPERATION_PRIVILEGE_NOT_ENOUGH);
		}
		
		if(family.getRequestinglist().remove(memberid) == null){
			return error(ErrorCode.NOT_REQUEST);
		}
		info.getRequestedfamily().remove(family.getFamilyid());
		
		SRejectRequestJoinFNotify notify = new SRejectRequestJoinFNotify();
		notify.family = FFamily.makeProtocolFamilyBasicInfo(family);
		notify.memberid = memberid;
		tsendWhileCommit(memberid, notify);
		FFamily.sendNotifyToChiefViceChief(family, notify);
		
		response(result);
		return true;
	}
}
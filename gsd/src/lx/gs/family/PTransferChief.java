package lx.gs.family;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.family.msg.CTransferChief;
import lx.gs.family.msg.STransferChief;
import xdb.Lockeys;

public class PTransferChief extends AProcedure<CTransferChief> {

	public PTransferChief(CTransferChief p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
	    final long memberid = param.memberid;
		lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid, memberid));
		
		STransferChief result = new STransferChief();

		if(memberid == roleid ){
			return error(ErrorCode.PARAM_ERROR);
		}
        xbean.Family family = FFamily.getFamilyByRoleId(roleid);
		if(!FFamily.isFamilyChief(roleid, family)){
			return error(ErrorCode.ONLY_CHIEF_CAN_TRANSFER_CHIEF);
		}

		xbean.FamilyMember fm = family.getMembers().get(memberid);
		
		if(!FFamily.transferFamilyChief(family, memberid)){
			return error(ErrorCode.PARAM_ERROR);
		}
		
		result.member = FFamily.makeProtocolFamilyMember(memberid, fm);
		response(result);
		return true;
	}

}

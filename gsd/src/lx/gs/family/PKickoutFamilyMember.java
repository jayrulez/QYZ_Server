package lx.gs.family;

import cfg.map.Reason;
import lx.gs.map.FMap;
import xbean.FamilyMember;
import xdb.Lockeys;
import gs.AProcedure;
import common.ErrorCode;
import lx.gs.family.msg.CKickoutFamilyMember;
import lx.gs.family.msg.SKickoutFamilyMember;
import lx.gs.family.msg.SKickoutFamilyMemberNotify;

import java.util.Map;

public class PKickoutFamilyMember extends AProcedure<CKickoutFamilyMember> {

	public PKickoutFamilyMember(CKickoutFamilyMember p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
	    final long memberid = param.memberid;
		lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid, memberid));

		if(memberid == roleid){
			return error(ErrorCode.PARAM_ERROR);
		}

        xbean.Family family = FFamily.getFamilyByRoleId(roleid);

        final Map<Long, xbean.FamilyMember> members = family.getMembers();
        final xbean.FamilyMember fm = members.remove(memberid);
        if(fm == null) {
			return error(ErrorCode.NOT_IN_FAMILY);
		}
		
		xbean.FamilyMember opfm = members.get(roleid);

		cfg.family.FamilyJob job = cfg.CfgMgr.familyjob.get(opfm.getFamilyjob());
		if(!job.kickoutperm || !job.appointjobs.contains(fm.getFamilyjob())){
			return error(ErrorCode.FAMILY_OPERATION_PRIVILEGE_NOT_ENOUGH);
		}

		FFamily.releaseFamilyJob(family, memberid);

        //踢出
        xbean.RoleFamily info = FFamily.getRoleFamilyInfo(memberid);
		info.setCurrentfid(0);

		family.setUpdatetime(System.currentTimeMillis());
		FFamily.onLeaveFamily(memberid);
        //从家族仙府里面踢出去
        if(FMap.isInFamily(memberid)){
            long mapid = FFamily.FamilyId2MapId.getOrDefault(family.getFamilyid(), 0L);
            if(mapid != 0){
                FMap.sendToKickFamilyMem(family.getFamilyid(), mapid, memberid, Reason.BE_KICK_OUT_FAMILY);
            }
        }
        //通知
        SKickoutFamilyMemberNotify notify = new SKickoutFamilyMemberNotify();
		notify.memberid = memberid;
		notify.family = FFamily.makeProtocolFamilyBasicInfo(family);
		tsendWhileCommit(memberid, notify);
		
		response(new SKickoutFamilyMember(memberid));
		return true;
	}

}

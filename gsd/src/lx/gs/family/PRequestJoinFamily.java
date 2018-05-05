package lx.gs.family;

import cfg.family.FamilyInfo;
import xdb.Lockeys;
import gs.AProcedure;
import common.ErrorCode;
import lx.gs.family.msg.CRequestJoinFamily;
import lx.gs.family.msg.SRequestJoinFamily;
import lx.gs.family.msg.SRequestJoinFamilyNotify;
import lx.gs.role.FRole;

public class PRequestJoinFamily extends AProcedure<CRequestJoinFamily> {

	public PRequestJoinFamily(CRequestJoinFamily p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid));
		lock(Lockeys.get(xtable.Locks.FAMILYLOCK, param.familyid));
		
		SRequestJoinFamily result = new SRequestJoinFamily();
		result.familyid = param.familyid;
		xbean.RoleFamily info = FFamily.getRoleFamilyInfo(roleid);
		

		if(!FFamily.isFamilyUnLocked(roleid)) return error(ErrorCode.FAMILY_MODULE_LOCKED);
		
//		if(info.getInitcrowdfamid() != 0){
//			return error(ErrorCode.ALREADY_IN_CROWD); //发起众筹后不能申请加入家族
//		}
		
		if(FFamily.isInQuitTimeLimit(info, cfg.family.FamilyInfo.DISALLOW_ACTION_HOUR_AFTER_QUIT)) 
			return error(ErrorCode.FAMILY_ACTION_DISALLOWED_AFATER_QUIT);
		
		xbean.Family family = FFamily.getFamilyByFamilyId(param.familyid);
		if(family == null) return error(ErrorCode.FAMILY_NOT_EXISTED);
		
		
		if(FFamily.isFamilyMember(family, roleid))
			return error(ErrorCode.FAMILY_ALREADY_IN_ONE);

        if(info.getRequestedfamily().size() >= FFamilyModule.FAMILY_MAX_APPLAY_NUM){
            return error(ErrorCode.APPLY_FAMILY_NUM_MAX);
        }

        if(info.getRequestedfamily().containsKey(param.familyid)){
            return error(ErrorCode.HAS_APPLAY_THIS_FAMILY);
        }
        //left 10 postions for manual manipulation
        if(family.getRequestinglist().size() >= (FFamily.leftPositions(family) + FFamilyModule.FAMILY_MAX_REQUEST_SIZE)){
            return error(ErrorCode.FAMILY_REQUEST_NUM_MAX);
        }

		long curtime = System.currentTimeMillis();
		info.getRequestedfamily().put(param.familyid, curtime);
		
		family.getRequestinglist().put(roleid, curtime);
		
		SRequestJoinFamilyNotify notify = new SRequestJoinFamilyNotify();
		FRole.genRoleShowInfo(roleid, notify.roleinfo);
		FFamily.sendNotifyToChiefViceChief(family, notify);
		
		response(result);
		return true;
	}

}

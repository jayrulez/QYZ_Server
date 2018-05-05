package lx.gs.family;

import xdb.Lockeys;
import gs.AProcedure;
import common.ErrorCode;
import lx.gs.family.msg.CAppointJob;
import lx.gs.family.msg.SAppointJob;
import lx.gs.family.msg.SAppointJobNotify;

public class PAppointJob extends AProcedure<CAppointJob> {

	public PAppointJob(CAppointJob p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
	    final long memberid = param.memberid;
		lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid, memberid));
		
		SAppointJob result = new SAppointJob();
		result.jobid = param.jobid;
		
		if(memberid == roleid){
			return error(ErrorCode.PARAM_ERROR);
		}

        xbean.Family family = FFamily.getFamilyByRoleId(roleid);
        if(!FFamily.isFamilyMember(family, memberid)){
            return error(ErrorCode.NOT_IN_FAMILY);
        }
	
		final int jobid = param.jobid;
		if(jobid == cfg.family.FamilyJobEnum.CHIEF){
			return error(ErrorCode.FAMILY_CHIEF_CAN_NOT_APPOINT);			
		}
		
		xbean.FamilyMember opfm = family.getMembers().get(roleid);
        final int myJob = opfm.getFamilyjob();
		cfg.family.FamilyJob job = cfg.CfgMgr.familyjob.get(myJob);
		if(!job.appointjobs.contains(jobid)){
            return error(ErrorCode.FAMILY_OPERATION_PRIVILEGE_NOT_ENOUGH);
		}


		xbean.FamilyMember fm = family.getMembers().get(memberid);
        final int memberJob = fm.getFamilyjob();

        if(jobid == memberJob) {
            return error(ErrorCode.PARAM_ERROR);
        }

        if(jobid == cfg.family.FamilyJobEnum.MEMBER){
			//免职操作
			if(memberJob == cfg.family.FamilyJobEnum.CHIEF){
				return error(ErrorCode.FAMILY_CHIEF_CAN_NOT_RELEASED);
			}
			if(myJob == cfg.family.FamilyJobEnum.VICE_CHIEF
					&& memberJob == cfg.family.FamilyJobEnum.VICE_CHIEF){
				return error(ErrorCode.FAMILY_JUST_CHIEF_CAN_RELEASE_VICE_CHIEF);
			}
            FFamily.removeJobStaff(family, memberJob, memberid);
		}
		else{
			if(memberJob != cfg.family.FamilyJobEnum.MEMBER){
				//已经有职位，要检查副组长不能任命副族长
				if(!FFamily.isFamilyChief(roleid, family) && memberJob == cfg.family.FamilyJobEnum.VICE_CHIEF){
					return error(ErrorCode.FAMILY_JUST_CHIEF_CAN_RELEASE_VICE_CHIEF);
				}
				FFamily.removeJobStaff(family, memberJob, memberid);
			}
			//任命
			if(!FFamily.addJobStaff(family, jobid, memberid)){
				return error(ErrorCode.JOB_STAFF_FULL);
			}	
		}
		//通知
		SAppointJobNotify notify = new SAppointJobNotify();
		notify.jobid = jobid;
		notify.operator = FFamily.makeProtocolFamilyMember(roleid, opfm);
		notify.member = FFamily.makeProtocolFamilyMember(memberid, fm);
		tsendWhileCommit(memberid, notify);
		
		//记日志
		FFamily.addFamilyLog(family, memberid, lx.gs.family.msg.FamilyLogReport.TYPE_PROMOTION, jobid);
		
		result.member = notify.member;
		response(result);
		return true;
	}

}

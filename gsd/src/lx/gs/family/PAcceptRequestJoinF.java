package lx.gs.family;

import lx.gs.family.msg.*;
import xdb.Lockeys;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import gs.AProcedure;
import common.ErrorCode;
import xdb.Transaction;

public class PAcceptRequestJoinF extends AProcedure<CAcceptRequestJoinF> {

	public PAcceptRequestJoinF(CAcceptRequestJoinF p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
	    final long other = param.memberid;
	    lock(xtable.Rolefamily.getTable(), Arrays.asList(roleid, other));
		SAcceptRequestJoinF result = new SAcceptRequestJoinF();

		final xbean.Family family = FFamily.getFamilyByRoleId(roleid);

		if(!FFamily.isFamilyLeader(roleid, family)){
			return error(ErrorCode.ONLY_CHIEF_VICECHIEF_CAN_ACTION);
		}
		//查看人数是否达到上限
		if(FFamily.isFamilyFull(family)){
			return error(ErrorCode.FAMILY_FULL);
		}		
		if(family.getRequestinglist().remove(other) == null){
			return error(ErrorCode.NOT_REQUEST);
		}
		xbean.FamilyMember opfm = family.getMembers().get(roleid);
		cfg.family.FamilyJob job = cfg.CfgMgr.familyjob.get(opfm.getFamilyjob());
		if(!job.enrollperm){
			return error(ErrorCode.FAMILY_OPERATION_PRIVILEGE_NOT_ENOUGH);
		}
		
		xbean.RoleFamily otherInfo = FFamily.getRoleFamilyInfo(other);
		Map<Long, Long> roleRequest = otherInfo.getRequestedfamily();
		if(otherInfo.getCurrentfid() > 0){
			//如果已经在其他家族里，需要删除申请信息，所以返回的是错误协议，但是存储过程是执行成功的。
            SAcceptFailNotify failNotify = new SAcceptFailNotify();
            failNotify.acceptid = other;
            xdb.Transaction.tsendWhileCommit(roleid, failNotify);
			return true;
		}
		final long myFamilyid = family.getFamilyid();
		roleRequest.remove(myFamilyid);
		otherInfo.setCurrentfid(myFamilyid);	//设置申请者的当前家族id
        if(!roleRequest.isEmpty()){
            for(long fid : roleRequest.keySet()) {
                Transaction.texecuteWhileCommit(new xdb.Procedure() {
                    @Override
                    protected boolean process() {
                        xbean.Family familyInfo = FFamily.getFamilyByFamilyId(fid);
                        if(familyInfo != null){
                            familyInfo.getRequestinglist().remove(other);
                        }
                        return true;
                    }
                });
            }
            roleRequest.clear();
        }
		xbean.FamilyMember newm =FFamily.createMemeberinfo(other);
		family.getMembers().put(other, newm);
		
		//记录日志
		FFamily.addFamilyLog(family, other, lx.gs.family.msg.FamilyLogReport.TYPE_JOIN_FAMILY, 0);
		FFamily.onJoinFamily(family, newm);

		SAcceptRequestJoinFNotify notify = new SAcceptRequestJoinFNotify();
		notify.family = FFamily.makeProtocolFamilyBasicInfo(family);
		notify.member = FFamily.makeProtocolFamilyMember(other, newm);
		tsendWhileCommit(other, notify);
		FFamily.sendNotifyToChiefViceChief(family, notify); //通知族长 副族长以及申请者
		
		xdb.Trace.info("PAcceptRequestJoinF: end, result={},memeberid={}", "true", other);
		result.member = FFamily.makeProtocolFamilyMember(other, newm);
		response(result);
		return true;
	}
}

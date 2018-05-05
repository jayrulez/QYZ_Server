
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.SError;
import lx.gs.family.FFamily;
import xdb.Lockeys;
import xdb.Transaction;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CResponseInvite__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CResponseInvite extends __CResponseInvite__ {
	@Override
	protected void process() {
		new AProcedure<CResponseInvite>(this){
            @Override
            protected boolean doProcess() throws Exception {
                xbean.RoleFamily roleFamily = FFamily.getRoleFamilyInfo(roleid);
                Lockeys.lock(xtable.Family.getTable(), Arrays.asList(familyid, roleFamily.getCurrentfid()));
                xbean.Family familyInfo = FFamily.getFamilyByFamilyId(familyid);
                if(!familyInfo.getInvitelist().containsKey(roleid)){
                    return false;
                }
                familyInfo.getInvitelist().remove(roleid);//不管是否同意都将其从邀请列表中删除
                if(result == 1){//如果同意加入家族
                    if(FFamily.isFamilyFull(familyInfo)){
                        error(ErrorCode.OTHER_FAMILY_HAS_FULL);
                        return true;
                    }
                    roleFamily.setCurrentfid(familyid);	//设置当前家族id
                    Map<Long, Long> roleRequest = roleFamily.getRequestedfamily();
                    roleRequest.remove(familyid);
                    if(roleRequest.size() > 0){
                        for(long fid : roleRequest.keySet()) {
                            Transaction.texecuteWhileCommit(new xdb.Procedure() {
                                @Override
                                protected boolean process() {
                                    xbean.Family requestFamily = FFamily.getFamilyByFamilyId(fid);
                                    if(requestFamily != null){
                                        requestFamily.getRequestinglist().remove(roleid);
                                    }
                                    return true;
                                }
                            });
                        }
                        roleRequest.clear();
                    }
                    xbean.FamilyMember newm =FFamily.createMemeberinfo(roleid);
                    familyInfo.getMembers().put(roleid, newm);
                    //记录日志
                    FFamily.addFamilyLog(familyInfo, roleid, lx.gs.family.msg.FamilyLogReport.TYPE_JOIN_FAMILY, 0);
                    FFamily.onJoinFamily(familyInfo, newm);
                    SAcceptRequestJoinFNotify notify = new SAcceptRequestJoinFNotify();
                    notify.family = FFamily.makeProtocolFamilyBasicInfo(familyInfo);
                    notify.member = FFamily.makeProtocolFamilyMember(roleid, newm);
                    xdb.Transaction.tsendWhileCommit(roleid, notify);
                    FFamily.sendNotifyToChiefViceChief(familyInfo, notify); //通知族长 副族长以及申请者
                }
                SResponseNotify rNotify = new SResponseNotify();
                rNotify.responseroleid = roleid;
                rNotify.responserolename = xtable.Roleinfos.selectName(roleid);
                rNotify.result = result;
                xdb.Transaction.tsendWhileCommit(inviteroleid, rNotify);
                SResponseInvite response = new SResponseInvite();
                response.result = result;
                response.familyname = familyInfo.getFamilyname();
                response(response);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578017;

	public int getType() {
		return 6578017;
	}

	public int result; // 回复邀请，0拒绝；1同意
	public long familyid;
	public long inviteroleid;

	public CResponseInvite() {
	}

	public CResponseInvite(int _result_, long _familyid_, long _inviteroleid_) {
		this.result = _result_;
		this.familyid = _familyid_;
		this.inviteroleid = _inviteroleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(result);
		_os_.marshal(familyid);
		_os_.marshal(inviteroleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		result = _os_.unmarshal_int();
		familyid = _os_.unmarshal_long();
		inviteroleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CResponseInvite) {
			CResponseInvite _o_ = (CResponseInvite)_o1_;
			if (result != _o_.result) return false;
			if (familyid != _o_.familyid) return false;
			if (inviteroleid != _o_.inviteroleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += result;
		_h_ += (int)familyid;
		_h_ += (int)inviteroleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(result).append(",");
		_sb_.append(familyid).append(",");
		_sb_.append(inviteroleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CResponseInvite _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = result - _o_.result;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(familyid - _o_.familyid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(inviteroleid - _o_.inviteroleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


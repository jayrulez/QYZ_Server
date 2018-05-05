
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.family.FFamily;
import xdb.Lockeys;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CInviteJoinFamily__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CInviteJoinFamily extends __CInviteJoinFamily__ {
	@Override
	protected void process() {
        new AProcedure<CInviteJoinFamily>(this){
            @Override
            protected boolean doProcess() throws Exception {
                lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid, beinviteroieid));
                xbean.Family familyInfo = FFamily.getFamilyByRoleId(roleid);
                if(familyInfo == null){
                    return false;
                }
                if(FFamily.isFamilyFull(familyInfo)){
                    return error(ErrorCode.FAMILY_FULL);
                }
                if(!FFamily.isFamilyLeader(roleid, familyInfo)){
                    return error(ErrorCode.ONLY_CHIEF_VICECHIEF_CAN_ACTION);
                }
                if(!FFamily.isFamilyUnLocked(beinviteroieid)){
                    return error(ErrorCode.FAMILY_MODULE_LOCKED);
                }
                xbean.RoleFamily roleFamily = FFamily.getRoleFamilyInfo(beinviteroieid);
                if(roleFamily.getCurrentfid() != 0){
                    return error(ErrorCode.HAS_IN_OTHER_FAMILY);
                }
                if(FFamily.isInQuitTimeLimit(roleFamily, cfg.family.FamilyInfo.DISALLOW_ACTION_HOUR_AFTER_QUIT))
                    return error(ErrorCode.OTHER_IS_IN_QUIT_TIME);
                familyInfo.getInvitelist().put(beinviteroieid, System.currentTimeMillis());
                SInviteJoinNotify notify = new SInviteJoinNotify();
                notify.familyid = familyInfo.getFamilyid();
                notify.familyname = familyInfo.getFamilyname();
                notify.inviteroleid = roleid;
                notify.inviterolename = xtable.Roleinfos.selectName(roleid);
                xdb.Transaction.tsendWhileCommit(beinviteroieid, notify);
                SInviteJoinFamily response = new SInviteJoinFamily();
                response.beinviteroieid = beinviteroieid;
                response(response);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6574548;

	public int getType() {
		return 6574548;
	}

	public long beinviteroieid;

	public CInviteJoinFamily() {
	}

	public CInviteJoinFamily(long _beinviteroieid_) {
		this.beinviteroieid = _beinviteroieid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(beinviteroieid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		beinviteroieid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CInviteJoinFamily) {
			CInviteJoinFamily _o_ = (CInviteJoinFamily)_o1_;
			if (beinviteroieid != _o_.beinviteroieid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)beinviteroieid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(beinviteroieid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CInviteJoinFamily _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(beinviteroieid - _o_.beinviteroieid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


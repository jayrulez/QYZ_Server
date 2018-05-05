
package lx.gs.team.msg;

import lx.gs.team.PUnFollowTeamMember;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUnFollowTeamMember__ extends xio.Protocol { }

/** 队长解除队友的跟随状态
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUnFollowTeamMember extends __CUnFollowTeamMember__ {
	@Override
	protected void process() {
		new PUnFollowTeamMember(this).validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557844;

	public int getType() {
		return 6557844;
	}

	public long unfollowmemberid;

	public CUnFollowTeamMember() {
	}

	public CUnFollowTeamMember(long _unfollowmemberid_) {
		this.unfollowmemberid = _unfollowmemberid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(unfollowmemberid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		unfollowmemberid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUnFollowTeamMember) {
			CUnFollowTeamMember _o_ = (CUnFollowTeamMember)_o1_;
			if (unfollowmemberid != _o_.unfollowmemberid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)unfollowmemberid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(unfollowmemberid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CUnFollowTeamMember _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(unfollowmemberid - _o_.unfollowmemberid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


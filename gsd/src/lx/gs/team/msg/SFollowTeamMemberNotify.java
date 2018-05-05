
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFollowTeamMemberNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFollowTeamMemberNotify extends __SFollowTeamMemberNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6574030;

	public int getType() {
		return 6574030;
	}

	public long followmemberid;

	public SFollowTeamMemberNotify() {
	}

	public SFollowTeamMemberNotify(long _followmemberid_) {
		this.followmemberid = _followmemberid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(followmemberid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		followmemberid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFollowTeamMemberNotify) {
			SFollowTeamMemberNotify _o_ = (SFollowTeamMemberNotify)_o1_;
			if (followmemberid != _o_.followmemberid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)followmemberid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(followmemberid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SFollowTeamMemberNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(followmemberid - _o_.followmemberid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


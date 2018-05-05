
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SUnFollowTeamMember__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SUnFollowTeamMember extends __SUnFollowTeamMember__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557845;

	public int getType() {
		return 6557845;
	}

	public final static int RES_OK = 0;

	public int result;
	public long unfollowmemberid;

	public SUnFollowTeamMember() {
	}

	public SUnFollowTeamMember(int _result_, long _unfollowmemberid_) {
		this.result = _result_;
		this.unfollowmemberid = _unfollowmemberid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(result);
		_os_.marshal(unfollowmemberid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		result = _os_.unmarshal_int();
		unfollowmemberid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SUnFollowTeamMember) {
			SUnFollowTeamMember _o_ = (SUnFollowTeamMember)_o1_;
			if (result != _o_.result) return false;
			if (unfollowmemberid != _o_.unfollowmemberid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += result;
		_h_ += (int)unfollowmemberid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(result).append(",");
		_sb_.append(unfollowmemberid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SUnFollowTeamMember _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = result - _o_.result;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(unfollowmemberid - _o_.unfollowmemberid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


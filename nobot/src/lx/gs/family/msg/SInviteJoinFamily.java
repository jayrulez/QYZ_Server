
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SInviteJoinFamily__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SInviteJoinFamily extends __SInviteJoinFamily__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564212;

	public int getType() {
		return 6564212;
	}

	public long beinviteroieid;

	public SInviteJoinFamily() {
	}

	public SInviteJoinFamily(long _beinviteroieid_) {
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
		if (_o1_ instanceof SInviteJoinFamily) {
			SInviteJoinFamily _o_ = (SInviteJoinFamily)_o1_;
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

	public int compareTo(SInviteJoinFamily _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(beinviteroieid - _o_.beinviteroieid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


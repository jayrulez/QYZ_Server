
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFriendDegreeNotify__ extends xio.Protocol { }

/** 通知友好度变化
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFriendDegreeNotify extends __SFriendDegreeNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565375;

	public int getType() {
		return 6565375;
	}

	public int notifytype;
	public long roleid;
	public int frienddegree;

	public SFriendDegreeNotify() {
	}

	public SFriendDegreeNotify(int _notifytype_, long _roleid_, int _frienddegree_) {
		this.notifytype = _notifytype_;
		this.roleid = _roleid_;
		this.frienddegree = _frienddegree_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(notifytype);
		_os_.marshal(roleid);
		_os_.marshal(frienddegree);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		notifytype = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		frienddegree = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFriendDegreeNotify) {
			SFriendDegreeNotify _o_ = (SFriendDegreeNotify)_o1_;
			if (notifytype != _o_.notifytype) return false;
			if (roleid != _o_.roleid) return false;
			if (frienddegree != _o_.frienddegree) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += notifytype;
		_h_ += (int)roleid;
		_h_ += frienddegree;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(notifytype).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(frienddegree).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SFriendDegreeNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = notifytype - _o_.notifytype;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		_c_ = frienddegree - _o_.frienddegree;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


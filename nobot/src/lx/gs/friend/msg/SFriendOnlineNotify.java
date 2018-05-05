
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFriendOnlineNotify__ extends xio.Protocol { }

/** 通知好友状态变化
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFriendOnlineNotify extends __SFriendOnlineNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576851;

	public int getType() {
		return 6576851;
	}

	public long roleid;
	public int online;

	public SFriendOnlineNotify() {
	}

	public SFriendOnlineNotify(long _roleid_, int _online_) {
		this.roleid = _roleid_;
		this.online = _online_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(online);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		online = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFriendOnlineNotify) {
			SFriendOnlineNotify _o_ = (SFriendOnlineNotify)_o1_;
			if (roleid != _o_.roleid) return false;
			if (online != _o_.online) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += online;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(online).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SFriendOnlineNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		_c_ = online - _o_.online;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


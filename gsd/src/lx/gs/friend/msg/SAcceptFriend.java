
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAcceptFriend__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAcceptFriend extends __SAcceptFriend__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553906;

	public int getType() {
		return 6553906;
	}

	public java.util.ArrayList<lx.gs.friend.msg.FriendInfo> friendlist;

	public SAcceptFriend() {
		friendlist = new java.util.ArrayList<lx.gs.friend.msg.FriendInfo>();
	}

	public SAcceptFriend(java.util.ArrayList<lx.gs.friend.msg.FriendInfo> _friendlist_) {
		this.friendlist = _friendlist_;
	}

	public final boolean _validator_() {
		for (lx.gs.friend.msg.FriendInfo _v_ : friendlist)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(friendlist.size());
		for (lx.gs.friend.msg.FriendInfo _v_ : friendlist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.friend.msg.FriendInfo _v_ = new lx.gs.friend.msg.FriendInfo();
			_v_.unmarshal(_os_);
			friendlist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAcceptFriend) {
			SAcceptFriend _o_ = (SAcceptFriend)_o1_;
			if (!friendlist.equals(_o_.friendlist)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += friendlist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(friendlist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


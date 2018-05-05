
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAcceptFriendNotify__ extends xio.Protocol { }

/** 别人接受我为好友的通知
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAcceptFriendNotify extends __SAcceptFriendNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553927;

	public int getType() {
		return 6553927;
	}

	public lx.gs.friend.msg.FriendInfo friend;

	public SAcceptFriendNotify() {
		friend = new lx.gs.friend.msg.FriendInfo();
	}

	public SAcceptFriendNotify(lx.gs.friend.msg.FriendInfo _friend_) {
		this.friend = _friend_;
	}

	public final boolean _validator_() {
		if (!friend._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(friend);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		friend.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAcceptFriendNotify) {
			SAcceptFriendNotify _o_ = (SAcceptFriendNotify)_o1_;
			if (!friend.equals(_o_.friend)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += friend.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(friend).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


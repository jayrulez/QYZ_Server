
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBlackFriend__ extends xio.Protocol { }

/** 屏蔽好友
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBlackFriend extends __SBlackFriend__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553916;

	public int getType() {
		return 6553916;
	}

	public java.util.ArrayList<lx.gs.friend.msg.RoleShowInfo> okroleidlist;

	public SBlackFriend() {
		okroleidlist = new java.util.ArrayList<lx.gs.friend.msg.RoleShowInfo>();
	}

	public SBlackFriend(java.util.ArrayList<lx.gs.friend.msg.RoleShowInfo> _okroleidlist_) {
		this.okroleidlist = _okroleidlist_;
	}

	public final boolean _validator_() {
		for (lx.gs.friend.msg.RoleShowInfo _v_ : okroleidlist)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(okroleidlist.size());
		for (lx.gs.friend.msg.RoleShowInfo _v_ : okroleidlist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.friend.msg.RoleShowInfo _v_ = new lx.gs.friend.msg.RoleShowInfo();
			_v_.unmarshal(_os_);
			okroleidlist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBlackFriend) {
			SBlackFriend _o_ = (SBlackFriend)_o1_;
			if (!okroleidlist.equals(_o_.okroleidlist)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += okroleidlist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(okroleidlist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


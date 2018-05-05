
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SSearchFriend__ extends xio.Protocol { }

/** 搜索好友结果
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SSearchFriend extends __SSearchFriend__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553914;

	public int getType() {
		return 6553914;
	}

	public java.lang.String searchkey;
	public java.util.ArrayList<lx.gs.friend.msg.RoleShowInfo> friendlist;

	public SSearchFriend() {
		searchkey = "";
		friendlist = new java.util.ArrayList<lx.gs.friend.msg.RoleShowInfo>();
	}

	public SSearchFriend(java.lang.String _searchkey_, java.util.ArrayList<lx.gs.friend.msg.RoleShowInfo> _friendlist_) {
		this.searchkey = _searchkey_;
		this.friendlist = _friendlist_;
	}

	public final boolean _validator_() {
		for (lx.gs.friend.msg.RoleShowInfo _v_ : friendlist)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(searchkey, "UTF-16LE");
		_os_.compact_uint32(friendlist.size());
		for (lx.gs.friend.msg.RoleShowInfo _v_ : friendlist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		searchkey = _os_.unmarshal_String("UTF-16LE");
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.friend.msg.RoleShowInfo _v_ = new lx.gs.friend.msg.RoleShowInfo();
			_v_.unmarshal(_os_);
			friendlist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SSearchFriend) {
			SSearchFriend _o_ = (SSearchFriend)_o1_;
			if (!searchkey.equals(_o_.searchkey)) return false;
			if (!friendlist.equals(_o_.friendlist)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += searchkey.hashCode();
		_h_ += friendlist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(searchkey.length()).append(",");
		_sb_.append(friendlist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


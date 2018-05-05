
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.friend.FFriend;
import lx.gs.friend.msg.SUnBlackFriend;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUnBlackFriend__ extends xio.Protocol { }

/** 解除屏蔽好友
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUnBlackFriend extends __CUnBlackFriend__ {
	@Override
	protected void process() {
		new AProcedure<CUnBlackFriend>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				SUnBlackFriend result = new SUnBlackFriend();
				xbean.RoleFriendsInfo myinfo = FFriend.getRoleFriendsInfo(roleid);

				for (long toid : param.roleidlist) {
					if (myinfo.getBlacklist().containsKey(toid)) {
						myinfo.getBlacklist().remove(toid);
						result.okroleidlist.add(toid);
						// SUnBlackFriendNotify broadcast = new
						// SUnBlackFriendNotify();
						// xbean.RoleFriend myrole = xbean.Pod.newRoleFriend();
						// myrole.setFollow(0);
						// myrole.setTime(System.currentTimeMillis());
						// broadcast.friend =
						// FFriend.makeProtocolFriendInfo(roleid, myrole);
						// tsend(toid, broadcast);
					}
				}
				return response(result);
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553917;

	public int getType() {
		return 6553917;
	}

	public java.util.ArrayList<Long> roleidlist;

	public CUnBlackFriend() {
		roleidlist = new java.util.ArrayList<Long>();
	}

	public CUnBlackFriend(java.util.ArrayList<Long> _roleidlist_) {
		this.roleidlist = _roleidlist_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(roleidlist.size());
		for (Long _v_ : roleidlist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			roleidlist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUnBlackFriend) {
			CUnBlackFriend _o_ = (CUnBlackFriend)_o1_;
			if (!roleidlist.equals(_o_.roleidlist)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += roleidlist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleidlist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

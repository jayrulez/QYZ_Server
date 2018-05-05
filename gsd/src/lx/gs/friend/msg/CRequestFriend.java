
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.friend.FFriend;
import lx.gs.friend.FFriendModule;
import lx.gs.friend.msg.SRequestFriend;
import lx.gs.friend.msg.SRequestFriendNotify;

import java.util.Arrays;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRequestFriend__ extends xio.Protocol { }

/** 申请好友
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRequestFriend extends __CRequestFriend__ {
	@Override
	protected void process() {
		new AProcedure<CRequestFriend>(this) {

			@Override
			protected boolean doProcess() throws Exception {
			    final long myid = roleid;
                final long toid = param.roleid;
				lock(xtable.Rolefriendsinfo.getTable(), Arrays.asList(myid, toid));
				SRequestFriend result = new SRequestFriend();
				xbean.RoleFriendsInfo myinfo = FFriend.getRoleFriendsInfo(myid);
				xbean.RoleFriendsInfo toinfo = FFriend.getRoleFriendsInfo(toid);
				if (myinfo.getFriends().size() >= FFriendModule.MAX_FRIENDLIST) {
					return error(ErrorCode.FRIEND_MY_FRIENDLIST_OVERFLOW);
				}
				if (toinfo.getFriends().size() >= FFriendModule.MAX_FRIENDLIST) {
					return error(ErrorCode.FRIEND_PEER_FRIENDLIST_OVERFLOW);
				}
				if (toinfo.getBlacklist().containsKey(myid)) {
					return error(ErrorCode.FRIEND_BLOCKED_BY_PEER);
				}
				if (toinfo.getFriends().containsKey(myid) || myinfo.getFriends().containsKey(toid)) {
					return error(ErrorCode.FRIEND_ALREADY_FRIEND);
				}
				if (toinfo.getRequesting().containsKey(myid)) {
					return error(ErrorCode.FRIEND_ALREADY_REQUESTING);
				}
				if (myinfo.getBlacklist().containsKey(toid)) {
					myinfo.getBlacklist().remove(toid);
				}

				long time = System.currentTimeMillis();
				toinfo.getRequesting().put(myid, time);

				result.friend = FFriend.makeProtocolRoleShowInfo(toid, 0);
				SRequestFriendNotify notify = new SRequestFriendNotify();
				notify.friend = FFriend.makeProtocolRoleShowInfo(myid, 0);
				tsend(toid, notify);
				response(result);
				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553903;

	public int getType() {
		return 6553903;
	}

	public long roleid;

	public CRequestFriend() {
	}

	public CRequestFriend(long _roleid_) {
		this.roleid = _roleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRequestFriend) {
			CRequestFriend _o_ = (CRequestFriend)_o1_;
			if (roleid != _o_.roleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CRequestFriend _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

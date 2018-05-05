
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.friend.FFriend;
import lx.gs.friend.msg.SDeleteFriend;
import lx.gs.friend.msg.SDeleteFriendNotify;
import xdb.Lockeys;

import java.util.Arrays;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CDeleteFriend__ extends xio.Protocol { }

/** 删除好友
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CDeleteFriend extends __CDeleteFriend__ {
	@Override
	protected void process() {
		new AProcedure<CDeleteFriend>(this) {

			@Override
			protected boolean doProcess() throws Exception {
			    final long myid = roleid;
                final long toid = param.roleid;
                Lockeys.lock(xtable.Rolefriendsinfo.getTable(), Arrays.asList(myid, toid));
				SDeleteFriend result = new SDeleteFriend();
				xbean.RoleFriendsInfo myinfo = FFriend.getRoleFriendsInfo(myid);
				xbean.RoleFriendsInfo toinfo = FFriend.getRoleFriendsInfo(toid);
				if (null == myinfo || null == toinfo) {
					return error(ErrorCode.FRIEND_NOT_FOUND);
				}
				int mmtype = myinfo.getFriends().remove(toid).getRelation();
				if(mmtype > 0){
					java.util.Map<Integer, xbean.RoleRelation> mymminfo = myinfo.getRelationinfo();
					mymminfo.get(mmtype).getRolelist().remove(toid);
					SDeleteMMNotify notify1 = new SDeleteMMNotify();
					notify1.mmtype = mmtype;
					notify1.roleid = toid;
					notify1.rolename = xtable.Roleinfos.selectName(toid);
					tsend(myid, notify1);
				}

				int mymmtype = toinfo.getFriends().remove(myid).getRelation();
				if(mymmtype>0){
					java.util.Map<Integer, xbean.RoleRelation> tomminfo = toinfo.getRelationinfo();
					tomminfo.get(mymmtype).getRolelist().remove(myid);
					SDeleteMMNotify notify2 = new SDeleteMMNotify();
					notify2.mmtype = mymmtype;
					notify2.roleid = myid;
					notify2.rolename = xtable.Roleinfos.selectName(myid);
					tsend(toid, notify2);
					
				}

				// 发送通知
				SDeleteFriendNotify notify = new SDeleteFriendNotify();
				notify.friend = FFriend.makeProtocolRoleShowInfo(myid, 0);
				tsend(toid, notify);

				result.roleid = toid;
				response(result);

				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553909;

	public int getType() {
		return 6553909;
	}

	public long roleid;

	public CDeleteFriend() {
	}

	public CDeleteFriend(long _roleid_) {
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
		if (_o1_ instanceof CDeleteFriend) {
			CDeleteFriend _o_ = (CDeleteFriend)_o1_;
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

	public int compareTo(CDeleteFriend _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

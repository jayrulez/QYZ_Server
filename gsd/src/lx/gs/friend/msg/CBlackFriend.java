
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import lx.gs.friend.FFriend;
import xbean.RoleFriend;
import xdb.Lockeys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBlackFriend__ extends xio.Protocol { }

/** 屏蔽好友
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBlackFriend extends __CBlackFriend__ {
	@Override
	protected void process() {
		new AProcedure<CBlackFriend>(this) {

			@Override
			protected boolean doProcess() throws Exception {
			    final List<Long> lockRoleids = new ArrayList<>(roleidlist);
                lockRoleids.add(roleid);
                Lockeys.lock(xtable.Rolefriendsinfo.getTable(), lockRoleids);
				SBlackFriend result = new SBlackFriend();
				xbean.RoleFriendsInfo myinfo = FFriend.getRoleFriendsInfo(roleid);
                Map<Long, RoleFriend> myFriends = myinfo.getFriends();
                final String myName = xtable.Roleinfos.selectName(roleid);
				for (long toroleid : param.roleidlist) {
				    final RoleFriend other = myFriends.remove(toroleid);
					if (other != null) {
						int mmtype = other.getRelation();
						if(mmtype != 0){
                            myinfo.getRelationinfo().get(mmtype).getRolelist().remove(toroleid);
							SDeleteMMNotify notify1 = new SDeleteMMNotify();
							notify1.mmtype = mmtype;
							notify1.roleid = toroleid;
							notify1.rolename = xtable.Roleinfos.selectName(toroleid);
							tsend(roleid, notify1);
						}

						final xbean.RoleFriendsInfo toinfo = FFriend.getRoleFriendsInfo(toroleid);
                        final Map<Long, RoleFriend> toFriends = toinfo.getFriends();
						int mymmtype = toFriends.get(roleid).getRelation();
						if(mymmtype != 0){
                            toinfo.getRelationinfo().get(mymmtype).getRolelist().remove(roleid);
							SDeleteMMNotify notify2 = new SDeleteMMNotify();
							notify2.mmtype = mymmtype;
							notify2.roleid = roleid;
							notify2.rolename = myName;
							tsend(toroleid, notify2);
						}
						toFriends.remove(roleid);
					}
                    result.okroleidlist.add(FFriend.makeProtocolRoleShowInfo(toroleid, FFriend.addBlackRole(roleid, toroleid)));
					SBlackFriendNotify broadcast = new SBlackFriendNotify();
					broadcast.friend = FFriend.makeProtocolRoleShowInfo(roleid, 0);
					tsend(toroleid, broadcast);
				}
				return response(result);
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553915;

	public int getType() {
		return 6553915;
	}

	public java.util.ArrayList<Long> roleidlist;

	public CBlackFriend() {
		roleidlist = new java.util.ArrayList<Long>();
	}

	public CBlackFriend(java.util.ArrayList<Long> _roleidlist_) {
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
		if (_o1_ instanceof CBlackFriend) {
			CBlackFriend _o_ = (CBlackFriend)_o1_;
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

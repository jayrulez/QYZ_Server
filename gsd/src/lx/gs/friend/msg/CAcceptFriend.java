
package lx.gs.friend.msg;

import cfg.achievement.CounterType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import common.ErrorCode;
import lx.gs.achievement.FAchievement;
import lx.gs.friend.FFriend;
import lx.gs.friend.FFriendModule;
import xbean.RoleFriend;
import xdb.Lockeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAcceptFriend__ extends xio.Protocol { }

/** 接受好友申请,全部接受也调用此协议
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAcceptFriend extends __CAcceptFriend__ {
	@Override
	protected void process() {
		new AProcedure<CAcceptFriend>(this) {
			@Override
			protected boolean doProcess() throws Exception {
			    final List<Long> lockRoleids = new ArrayList<Long>(roleidlist);
                lockRoleids.add(roleid);
                Lockeys.lock(xtable.Rolefriendsinfo.getTable(), lockRoleids);
				lx.gs.friend.msg.SAcceptFriend result = new lx.gs.friend.msg.SAcceptFriend();
				xbean.RoleFriendsInfo myinfo = FFriend.getRoleFriendsInfo(roleid);

                Map<Long, RoleFriend> myFriends = myinfo.getFriends();
				if (myFriends.size() >= FFriendModule.MAX_FRIENDLIST) {
					return error(ErrorCode.FRIEND_MY_FRIENDLIST_OVERFLOW);
				}

				Map<Long, Long> myRequesing = myinfo.getRequesting();

				for (long toroleid : roleidlist) {
				    if(toroleid == roleid) return false;
                    if(myFriends.size() >=  FFriendModule.MAX_FRIENDLIST) {
                        myRequesing.clear();
                        break;
                    }
                    if(myinfo.getRequesting().remove(toroleid) == null)
                        continue;
					xbean.RoleFriendsInfo toinfo = FFriend.getRoleFriendsInfo(toroleid);
                    Map<Long, RoleFriend> toFriends = toinfo.getFriends();
					if (toFriends.size() >= FFriendModule.MAX_FRIENDLIST) {
						myinfo.getRequesting().remove(toroleid);
						continue;
					}

					long now = System.currentTimeMillis();
					xbean.RoleFriend newfriend = xbean.Pod.newRoleFriend();
					newfriend.setFrienddegress(0);
					newfriend.setRelation(0);
					newfriend.setTime(now);
					myinfo.getFriends().put(toroleid, newfriend);

					xbean.RoleFriend tofriend = xbean.Pod.newRoleFriend();
					tofriend.setFrienddegress(0);
					tofriend.setRelation(0);
					tofriend.setTime(now);
					toinfo.getFriends().put(roleid, tofriend);

					result.friendlist.add(FFriend.makeProtocolBasicFriendInfo(toroleid, newfriend));

					SAcceptFriendNotify notify = new SAcceptFriendNotify();
					notify.friend = FFriend.makeProtocolBasicFriendInfo(roleid, tofriend);
					tsend(toroleid, notify);

					//好友数统计取历史最高值
					FAchievement.updateCounter(toroleid, CounterType.FRIEND_NUM, toFriends.size());
				}

				FAchievement.updateCounter(roleid, CounterType.FRIEND_NUM, myFriends.size());

				return response(result);
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553905;

	public int getType() {
		return 6553905;
	}

	public java.util.ArrayList<Long> roleidlist;

	public CAcceptFriend() {
		roleidlist = new java.util.ArrayList<Long>();
	}

	public CAcceptFriend(java.util.ArrayList<Long> _roleidlist_) {
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
		if (_o1_ instanceof CAcceptFriend) {
			CAcceptFriend _o_ = (CAcceptFriend)_o1_;
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

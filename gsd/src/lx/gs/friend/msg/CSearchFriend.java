
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.friend.FFriend;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSearchFriend__ extends xio.Protocol { }

/** 搜索好友,查找，刷新都调用此协议
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSearchFriend extends __CSearchFriend__ {
	@Override
	protected void process() {
		new AProcedure<CSearchFriend>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				SSearchFriend result = new SSearchFriend();
				String searchKey = param.searchkey.trim();
				if (searchKey.isEmpty()) {
					// 随机选择8个好友，自动给玩家查找等级和玩家相差正负10战斗力差距不超过10%的的玩家八个
					java.util.List<RoleShowInfo> friends = FFriend.getRandomOnLineRoles(roleid);
					result.friendlist.addAll(friends);
					return response(result);
				} else {
					long searchid;
					try {
						searchid = Long.parseLong(searchKey);
                        if(xtable.Roleinfos.selectLevel(searchid) == null) {
                            searchid = 0;
                        }
					} catch (Exception e) {
						searchid = 0L;
					}
					if (searchid == 0) {
						String fullname = gs.Utils.makeFullName(searchKey, gs.Utils.getServerId());
						Long tempId = xtable.Rolename2ids.select(fullname);
						if (tempId != null) {
							searchid = tempId;
						} else {
                            return error(ErrorCode.FRIEND_NOT_FOUND);
                        }
					}
					result.searchkey = param.searchkey;
					result.friendlist.add(FFriend.makeProtocolRoleShowInfo(searchid, 0));
					return response(result);
				}
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553913;

	public int getType() {
		return 6553913;
	}

	public java.lang.String searchkey;

	public CSearchFriend() {
		searchkey = "";
	}

	public CSearchFriend(java.lang.String _searchkey_) {
		this.searchkey = _searchkey_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(searchkey, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		searchkey = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSearchFriend) {
			CSearchFriend _o_ = (CSearchFriend)_o1_;
			if (!searchkey.equals(_o_.searchkey)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += searchkey.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(searchkey.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

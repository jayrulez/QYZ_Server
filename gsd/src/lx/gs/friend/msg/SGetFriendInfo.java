
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetFriendInfo__ extends xio.Protocol { }

/** 服务器不保存玩家是否查看过请求列表，所以如果要实现玩家查看过请求列表后，下次登陆好友按钮不要有小红点，请客户端保存
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetFriendInfo extends __SGetFriendInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553902;

	public int getType() {
		return 6553902;
	}

	public lx.gs.friend.msg.RoleFriendInfo myinfo;
	public java.util.HashMap<Long,lx.gs.friend.msg.FriendInfo> friendinfo;
	public java.util.HashMap<Long,lx.gs.friend.msg.RoleShowInfo> requestinginfo;
	public java.util.HashMap<Long,lx.gs.friend.msg.RoleShowInfo> blackinfo;
	public java.util.HashMap<Long,lx.gs.friend.msg.EnemyShowInfo> enemyinfo;
	public java.util.HashMap<Long,lx.gs.friend.msg.IdolInfo> idolcharminfo;

	public SGetFriendInfo() {
		myinfo = new lx.gs.friend.msg.RoleFriendInfo();
		friendinfo = new java.util.HashMap<Long,lx.gs.friend.msg.FriendInfo>();
		requestinginfo = new java.util.HashMap<Long,lx.gs.friend.msg.RoleShowInfo>();
		blackinfo = new java.util.HashMap<Long,lx.gs.friend.msg.RoleShowInfo>();
		enemyinfo = new java.util.HashMap<Long,lx.gs.friend.msg.EnemyShowInfo>();
		idolcharminfo = new java.util.HashMap<Long,lx.gs.friend.msg.IdolInfo>();
	}

	public SGetFriendInfo(lx.gs.friend.msg.RoleFriendInfo _myinfo_, java.util.HashMap<Long,lx.gs.friend.msg.FriendInfo> _friendinfo_, java.util.HashMap<Long,lx.gs.friend.msg.RoleShowInfo> _requestinginfo_, java.util.HashMap<Long,lx.gs.friend.msg.RoleShowInfo> _blackinfo_, java.util.HashMap<Long,lx.gs.friend.msg.EnemyShowInfo> _enemyinfo_, java.util.HashMap<Long,lx.gs.friend.msg.IdolInfo> _idolcharminfo_) {
		this.myinfo = _myinfo_;
		this.friendinfo = _friendinfo_;
		this.requestinginfo = _requestinginfo_;
		this.blackinfo = _blackinfo_;
		this.enemyinfo = _enemyinfo_;
		this.idolcharminfo = _idolcharminfo_;
	}

	public final boolean _validator_() {
		if (!myinfo._validator_()) return false;
		for (java.util.Map.Entry<Long, lx.gs.friend.msg.FriendInfo> _e_ : friendinfo.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		for (java.util.Map.Entry<Long, lx.gs.friend.msg.RoleShowInfo> _e_ : requestinginfo.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		for (java.util.Map.Entry<Long, lx.gs.friend.msg.RoleShowInfo> _e_ : blackinfo.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		for (java.util.Map.Entry<Long, lx.gs.friend.msg.EnemyShowInfo> _e_ : enemyinfo.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		for (java.util.Map.Entry<Long, lx.gs.friend.msg.IdolInfo> _e_ : idolcharminfo.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(myinfo);
		_os_.compact_uint32(friendinfo.size());
		for (java.util.Map.Entry<Long, lx.gs.friend.msg.FriendInfo> _e_ : friendinfo.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(requestinginfo.size());
		for (java.util.Map.Entry<Long, lx.gs.friend.msg.RoleShowInfo> _e_ : requestinginfo.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(blackinfo.size());
		for (java.util.Map.Entry<Long, lx.gs.friend.msg.RoleShowInfo> _e_ : blackinfo.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(enemyinfo.size());
		for (java.util.Map.Entry<Long, lx.gs.friend.msg.EnemyShowInfo> _e_ : enemyinfo.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(idolcharminfo.size());
		for (java.util.Map.Entry<Long, lx.gs.friend.msg.IdolInfo> _e_ : idolcharminfo.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		myinfo.unmarshal(_os_);
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			lx.gs.friend.msg.FriendInfo _v_ = new lx.gs.friend.msg.FriendInfo();
			_v_.unmarshal(_os_);
			friendinfo.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			lx.gs.friend.msg.RoleShowInfo _v_ = new lx.gs.friend.msg.RoleShowInfo();
			_v_.unmarshal(_os_);
			requestinginfo.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			lx.gs.friend.msg.RoleShowInfo _v_ = new lx.gs.friend.msg.RoleShowInfo();
			_v_.unmarshal(_os_);
			blackinfo.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			lx.gs.friend.msg.EnemyShowInfo _v_ = new lx.gs.friend.msg.EnemyShowInfo();
			_v_.unmarshal(_os_);
			enemyinfo.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			lx.gs.friend.msg.IdolInfo _v_ = new lx.gs.friend.msg.IdolInfo();
			_v_.unmarshal(_os_);
			idolcharminfo.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetFriendInfo) {
			SGetFriendInfo _o_ = (SGetFriendInfo)_o1_;
			if (!myinfo.equals(_o_.myinfo)) return false;
			if (!friendinfo.equals(_o_.friendinfo)) return false;
			if (!requestinginfo.equals(_o_.requestinginfo)) return false;
			if (!blackinfo.equals(_o_.blackinfo)) return false;
			if (!enemyinfo.equals(_o_.enemyinfo)) return false;
			if (!idolcharminfo.equals(_o_.idolcharminfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += myinfo.hashCode();
		_h_ += friendinfo.hashCode();
		_h_ += requestinginfo.hashCode();
		_h_ += blackinfo.hashCode();
		_h_ += enemyinfo.hashCode();
		_h_ += idolcharminfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(myinfo).append(",");
		_sb_.append(friendinfo).append(",");
		_sb_.append(requestinginfo).append(",");
		_sb_.append(blackinfo).append(",");
		_sb_.append(enemyinfo).append(",");
		_sb_.append(idolcharminfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


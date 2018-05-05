
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class FriendInfo implements Marshal {
	public lx.gs.friend.msg.RoleShowInfo roleinfo;
	public int charmdegree;
	public int frienddegree; // 我与好友的友好度，根据我送给对方花的数量决定
	public int relation; // 我与好友的关系

	public FriendInfo() {
		roleinfo = new lx.gs.friend.msg.RoleShowInfo();
	}

	public FriendInfo(lx.gs.friend.msg.RoleShowInfo _roleinfo_, int _charmdegree_, int _frienddegree_, int _relation_) {
		this.roleinfo = _roleinfo_;
		this.charmdegree = _charmdegree_;
		this.frienddegree = _frienddegree_;
		this.relation = _relation_;
	}

	public final boolean _validator_() {
		if (!roleinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleinfo);
		_os_.marshal(charmdegree);
		_os_.marshal(frienddegree);
		_os_.marshal(relation);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleinfo.unmarshal(_os_);
		charmdegree = _os_.unmarshal_int();
		frienddegree = _os_.unmarshal_int();
		relation = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FriendInfo) {
			FriendInfo _o_ = (FriendInfo)_o1_;
			if (!roleinfo.equals(_o_.roleinfo)) return false;
			if (charmdegree != _o_.charmdegree) return false;
			if (frienddegree != _o_.frienddegree) return false;
			if (relation != _o_.relation) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += roleinfo.hashCode();
		_h_ += charmdegree;
		_h_ += frienddegree;
		_h_ += relation;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleinfo).append(",");
		_sb_.append(charmdegree).append(",");
		_sb_.append(frienddegree).append(",");
		_sb_.append(relation).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


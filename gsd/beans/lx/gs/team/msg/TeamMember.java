
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class TeamMember implements Marshal {
	public long roleid;
	public long jointime; // 入队时间
	public int follow; // 是否跟随，1为跟随，0为不跟随
	public lx.gs.role.msg.RoleShowInfo4 roleinfo; // 角色的其他信息

	public TeamMember() {
		roleinfo = new lx.gs.role.msg.RoleShowInfo4();
	}

	public TeamMember(long _roleid_, long _jointime_, int _follow_, lx.gs.role.msg.RoleShowInfo4 _roleinfo_) {
		this.roleid = _roleid_;
		this.jointime = _jointime_;
		this.follow = _follow_;
		this.roleinfo = _roleinfo_;
	}

	public final boolean _validator_() {
		if (!roleinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(jointime);
		_os_.marshal(follow);
		_os_.marshal(roleinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		jointime = _os_.unmarshal_long();
		follow = _os_.unmarshal_int();
		roleinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TeamMember) {
			TeamMember _o_ = (TeamMember)_o1_;
			if (roleid != _o_.roleid) return false;
			if (jointime != _o_.jointime) return false;
			if (follow != _o_.follow) return false;
			if (!roleinfo.equals(_o_.roleinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += (int)jointime;
		_h_ += follow;
		_h_ += roleinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(jointime).append(",");
		_sb_.append(follow).append(",");
		_sb_.append(roleinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


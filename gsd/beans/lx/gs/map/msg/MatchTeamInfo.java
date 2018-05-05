
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class MatchTeamInfo implements Marshal {
	public java.util.ArrayList<lx.gs.role.msg.RoleShowInfo4> members;

	public MatchTeamInfo() {
		members = new java.util.ArrayList<lx.gs.role.msg.RoleShowInfo4>();
	}

	public MatchTeamInfo(java.util.ArrayList<lx.gs.role.msg.RoleShowInfo4> _members_) {
		this.members = _members_;
	}

	public final boolean _validator_() {
		for (lx.gs.role.msg.RoleShowInfo4 _v_ : members)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(members.size());
		for (lx.gs.role.msg.RoleShowInfo4 _v_ : members) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.role.msg.RoleShowInfo4 _v_ = new lx.gs.role.msg.RoleShowInfo4();
			_v_.unmarshal(_os_);
			members.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MatchTeamInfo) {
			MatchTeamInfo _o_ = (MatchTeamInfo)_o1_;
			if (!members.equals(_o_.members)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += members.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(members).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


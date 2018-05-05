
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class SingleRoleBonus implements Marshal {
	public long roleid;
	public java.lang.String rolename;
	public map.msg.Bonus rolebonus;

	public SingleRoleBonus() {
		rolename = "";
		rolebonus = new map.msg.Bonus();
	}

	public SingleRoleBonus(long _roleid_, java.lang.String _rolename_, map.msg.Bonus _rolebonus_) {
		this.roleid = _roleid_;
		this.rolename = _rolename_;
		this.rolebonus = _rolebonus_;
	}

	public final boolean _validator_() {
		if (!rolebonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(rolename, "UTF-16LE");
		_os_.marshal(rolebonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		rolename = _os_.unmarshal_String("UTF-16LE");
		rolebonus.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SingleRoleBonus) {
			SingleRoleBonus _o_ = (SingleRoleBonus)_o1_;
			if (roleid != _o_.roleid) return false;
			if (!rolename.equals(_o_.rolename)) return false;
			if (!rolebonus.equals(_o_.rolebonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += rolename.hashCode();
		_h_ += rolebonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append("T").append(rolename.length()).append(",");
		_sb_.append(rolebonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


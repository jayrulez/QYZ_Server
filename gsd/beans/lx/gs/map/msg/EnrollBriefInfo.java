
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class EnrollBriefInfo implements Marshal {
	public long roleid;
	public int gender;
	public java.lang.String name;
	public int profession;

	public EnrollBriefInfo() {
		name = "";
	}

	public EnrollBriefInfo(long _roleid_, int _gender_, java.lang.String _name_, int _profession_) {
		this.roleid = _roleid_;
		this.gender = _gender_;
		this.name = _name_;
		this.profession = _profession_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(gender);
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(profession);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		gender = _os_.unmarshal_int();
		name = _os_.unmarshal_String("UTF-16LE");
		profession = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof EnrollBriefInfo) {
			EnrollBriefInfo _o_ = (EnrollBriefInfo)_o1_;
			if (roleid != _o_.roleid) return false;
			if (gender != _o_.gender) return false;
			if (!name.equals(_o_.name)) return false;
			if (profession != _o_.profession) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += gender;
		_h_ += name.hashCode();
		_h_ += profession;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(gender).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


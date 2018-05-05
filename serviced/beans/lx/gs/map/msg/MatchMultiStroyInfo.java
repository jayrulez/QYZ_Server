
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class MatchMultiStroyInfo implements Marshal {
	public long roleid;
	public int gender;
	public java.lang.String name;
	public int profession;
	public int star;
	public int usedtimes;
	public int level;

	public MatchMultiStroyInfo() {
		name = "";
	}

	public MatchMultiStroyInfo(long _roleid_, int _gender_, java.lang.String _name_, int _profession_, int _star_, int _usedtimes_, int _level_) {
		this.roleid = _roleid_;
		this.gender = _gender_;
		this.name = _name_;
		this.profession = _profession_;
		this.star = _star_;
		this.usedtimes = _usedtimes_;
		this.level = _level_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(gender);
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(profession);
		_os_.marshal(star);
		_os_.marshal(usedtimes);
		_os_.marshal(level);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		gender = _os_.unmarshal_int();
		name = _os_.unmarshal_String("UTF-16LE");
		profession = _os_.unmarshal_int();
		star = _os_.unmarshal_int();
		usedtimes = _os_.unmarshal_int();
		level = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MatchMultiStroyInfo) {
			MatchMultiStroyInfo _o_ = (MatchMultiStroyInfo)_o1_;
			if (roleid != _o_.roleid) return false;
			if (gender != _o_.gender) return false;
			if (!name.equals(_o_.name)) return false;
			if (profession != _o_.profession) return false;
			if (star != _o_.star) return false;
			if (usedtimes != _o_.usedtimes) return false;
			if (level != _o_.level) return false;
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
		_h_ += star;
		_h_ += usedtimes;
		_h_ += level;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(gender).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(star).append(",");
		_sb_.append(usedtimes).append(",");
		_sb_.append(level).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


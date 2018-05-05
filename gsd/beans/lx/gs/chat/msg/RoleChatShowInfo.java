
package lx.gs.chat.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RoleChatShowInfo implements Marshal {
	public java.lang.String name;
	public int gender;
	public int profession;
	public int level;
	public int viplevel;
	public byte isonline;

	public RoleChatShowInfo() {
		name = "";
	}

	public RoleChatShowInfo(java.lang.String _name_, int _gender_, int _profession_, int _level_, int _viplevel_, byte _isonline_) {
		this.name = _name_;
		this.gender = _gender_;
		this.profession = _profession_;
		this.level = _level_;
		this.viplevel = _viplevel_;
		this.isonline = _isonline_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(gender);
		_os_.marshal(profession);
		_os_.marshal(level);
		_os_.marshal(viplevel);
		_os_.marshal(isonline);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		name = _os_.unmarshal_String("UTF-16LE");
		gender = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		level = _os_.unmarshal_int();
		viplevel = _os_.unmarshal_int();
		isonline = _os_.unmarshal_byte();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleChatShowInfo) {
			RoleChatShowInfo _o_ = (RoleChatShowInfo)_o1_;
			if (!name.equals(_o_.name)) return false;
			if (gender != _o_.gender) return false;
			if (profession != _o_.profession) return false;
			if (level != _o_.level) return false;
			if (viplevel != _o_.viplevel) return false;
			if (isonline != _o_.isonline) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += name.hashCode();
		_h_ += gender;
		_h_ += profession;
		_h_ += level;
		_h_ += viplevel;
		_h_ += (int)isonline;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(gender).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(level).append(",");
		_sb_.append(viplevel).append(",");
		_sb_.append(isonline).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}



package lx.gs.rank.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class GeneralRankInfo implements Marshal {
	public long roleid;
	public java.lang.String name;
	public int level;
	public long value;

	public GeneralRankInfo() {
		name = "";
	}

	public GeneralRankInfo(long _roleid_, java.lang.String _name_, int _level_, long _value_) {
		this.roleid = _roleid_;
		this.name = _name_;
		this.level = _level_;
		this.value = _value_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(level);
		_os_.marshal(value);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		name = _os_.unmarshal_String("UTF-16LE");
		level = _os_.unmarshal_int();
		value = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GeneralRankInfo) {
			GeneralRankInfo _o_ = (GeneralRankInfo)_o1_;
			if (roleid != _o_.roleid) return false;
			if (!name.equals(_o_.name)) return false;
			if (level != _o_.level) return false;
			if (value != _o_.value) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += name.hashCode();
		_h_ += level;
		_h_ += (int)value;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(level).append(",");
		_sb_.append(value).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


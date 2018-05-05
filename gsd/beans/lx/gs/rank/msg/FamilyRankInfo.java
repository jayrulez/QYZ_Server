
package lx.gs.rank.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class FamilyRankInfo implements Marshal {
	public long id;
	public java.lang.String name;
	public int level;
	public long value; // 家族建设度
	public long chiefid; // 族长id

	public FamilyRankInfo() {
		name = "";
	}

	public FamilyRankInfo(long _id_, java.lang.String _name_, int _level_, long _value_, long _chiefid_) {
		this.id = _id_;
		this.name = _name_;
		this.level = _level_;
		this.value = _value_;
		this.chiefid = _chiefid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(level);
		_os_.marshal(value);
		_os_.marshal(chiefid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_long();
		name = _os_.unmarshal_String("UTF-16LE");
		level = _os_.unmarshal_int();
		value = _os_.unmarshal_long();
		chiefid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FamilyRankInfo) {
			FamilyRankInfo _o_ = (FamilyRankInfo)_o1_;
			if (id != _o_.id) return false;
			if (!name.equals(_o_.name)) return false;
			if (level != _o_.level) return false;
			if (value != _o_.value) return false;
			if (chiefid != _o_.chiefid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)id;
		_h_ += name.hashCode();
		_h_ += level;
		_h_ += (int)value;
		_h_ += (int)chiefid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(level).append(",");
		_sb_.append(value).append(",");
		_sb_.append(chiefid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


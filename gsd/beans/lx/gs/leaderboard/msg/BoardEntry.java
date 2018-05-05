
package lx.gs.leaderboard.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class BoardEntry implements Marshal {
	public long id;
	public java.lang.String name;
	public long val1;
	public int val2;

	public BoardEntry() {
		name = "";
	}

	public BoardEntry(long _id_, java.lang.String _name_, long _val1_, int _val2_) {
		this.id = _id_;
		this.name = _name_;
		this.val1 = _val1_;
		this.val2 = _val2_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(val1);
		_os_.marshal(val2);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_long();
		name = _os_.unmarshal_String("UTF-16LE");
		val1 = _os_.unmarshal_long();
		val2 = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BoardEntry) {
			BoardEntry _o_ = (BoardEntry)_o1_;
			if (id != _o_.id) return false;
			if (!name.equals(_o_.name)) return false;
			if (val1 != _o_.val1) return false;
			if (val2 != _o_.val2) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)id;
		_h_ += name.hashCode();
		_h_ += (int)val1;
		_h_ += val2;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(val1).append(",");
		_sb_.append(val2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


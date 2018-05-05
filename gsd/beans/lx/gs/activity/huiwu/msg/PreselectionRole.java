
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class PreselectionRole implements Marshal {
	public long roleid;
	public java.lang.String name;
	public int combatpower;
	public int beguessnum;

	public PreselectionRole() {
		name = "";
	}

	public PreselectionRole(long _roleid_, java.lang.String _name_, int _combatpower_, int _beguessnum_) {
		this.roleid = _roleid_;
		this.name = _name_;
		this.combatpower = _combatpower_;
		this.beguessnum = _beguessnum_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(combatpower);
		_os_.marshal(beguessnum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		name = _os_.unmarshal_String("UTF-16LE");
		combatpower = _os_.unmarshal_int();
		beguessnum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PreselectionRole) {
			PreselectionRole _o_ = (PreselectionRole)_o1_;
			if (roleid != _o_.roleid) return false;
			if (!name.equals(_o_.name)) return false;
			if (combatpower != _o_.combatpower) return false;
			if (beguessnum != _o_.beguessnum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += name.hashCode();
		_h_ += combatpower;
		_h_ += beguessnum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(combatpower).append(",");
		_sb_.append(beguessnum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


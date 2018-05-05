
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class MemberStatistic implements Marshal {
	public java.lang.String name;
	public java.lang.String ownername;
	public int damage;
	public int kill;
	public int rune;

	public MemberStatistic() {
		name = "";
		ownername = "";
	}

	public MemberStatistic(java.lang.String _name_, java.lang.String _ownername_, int _damage_, int _kill_, int _rune_) {
		this.name = _name_;
		this.ownername = _ownername_;
		this.damage = _damage_;
		this.kill = _kill_;
		this.rune = _rune_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(ownername, "UTF-16LE");
		_os_.marshal(damage);
		_os_.marshal(kill);
		_os_.marshal(rune);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		name = _os_.unmarshal_String("UTF-16LE");
		ownername = _os_.unmarshal_String("UTF-16LE");
		damage = _os_.unmarshal_int();
		kill = _os_.unmarshal_int();
		rune = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MemberStatistic) {
			MemberStatistic _o_ = (MemberStatistic)_o1_;
			if (!name.equals(_o_.name)) return false;
			if (!ownername.equals(_o_.ownername)) return false;
			if (damage != _o_.damage) return false;
			if (kill != _o_.kill) return false;
			if (rune != _o_.rune) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += name.hashCode();
		_h_ += ownername.hashCode();
		_h_ += damage;
		_h_ += kill;
		_h_ += rune;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append("T").append(ownername.length()).append(",");
		_sb_.append(damage).append(",");
		_sb_.append(kill).append(",");
		_sb_.append(rune).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


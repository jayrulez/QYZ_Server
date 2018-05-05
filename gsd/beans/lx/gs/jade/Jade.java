
package lx.gs.jade;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Jade implements Marshal , Comparable<Jade>{
	public int level; // 玉佩等级
	public int bonus; // 玉佩增加值

	public Jade() {
	}

	public Jade(int _level_, int _bonus_) {
		this.level = _level_;
		this.bonus = _bonus_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(level);
		_os_.marshal(bonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		level = _os_.unmarshal_int();
		bonus = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Jade) {
			Jade _o_ = (Jade)_o1_;
			if (level != _o_.level) return false;
			if (bonus != _o_.bonus) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += level;
		_h_ += bonus;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(level).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(Jade _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = level - _o_.level;
		if (0 != _c_) return _c_;
		_c_ = bonus - _o_.bonus;
		if (0 != _c_) return _c_;
		return _c_;
	}

}


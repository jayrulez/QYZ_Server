
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class HealResult implements Marshal , Comparable<HealResult>{
	public long defencerid;
	public int heal;
	public int hp;

	public HealResult() {
	}

	public HealResult(long _defencerid_, int _heal_, int _hp_) {
		this.defencerid = _defencerid_;
		this.heal = _heal_;
		this.hp = _hp_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(defencerid);
		_os_.marshal(heal);
		_os_.marshal(hp);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		defencerid = _os_.unmarshal_long();
		heal = _os_.unmarshal_int();
		hp = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof HealResult) {
			HealResult _o_ = (HealResult)_o1_;
			if (defencerid != _o_.defencerid) return false;
			if (heal != _o_.heal) return false;
			if (hp != _o_.hp) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)defencerid;
		_h_ += heal;
		_h_ += hp;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(defencerid).append(",");
		_sb_.append(heal).append(",");
		_sb_.append(hp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(HealResult _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(defencerid - _o_.defencerid);
		if (0 != _c_) return _c_;
		_c_ = heal - _o_.heal;
		if (0 != _c_) return _c_;
		_c_ = hp - _o_.hp;
		if (0 != _c_) return _c_;
		return _c_;
	}

}


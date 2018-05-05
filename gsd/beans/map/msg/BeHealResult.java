
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class BeHealResult implements Marshal , Comparable<BeHealResult>{
	public int heal;

	public BeHealResult() {
	}

	public BeHealResult(int _heal_) {
		this.heal = _heal_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(heal);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		heal = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BeHealResult) {
			BeHealResult _o_ = (BeHealResult)_o1_;
			if (heal != _o_.heal) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += heal;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(heal).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(BeHealResult _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = heal - _o_.heal;
		if (0 != _c_) return _c_;
		return _c_;
	}

}


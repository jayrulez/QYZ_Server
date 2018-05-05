
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class BeAttackResult implements Marshal , Comparable<BeAttackResult>{
	public byte ismiss;
	public byte iscrit;
	public byte isexcellent;
	public byte islucky;
	public int attack;

	public BeAttackResult() {
	}

	public BeAttackResult(byte _ismiss_, byte _iscrit_, byte _isexcellent_, byte _islucky_, int _attack_) {
		this.ismiss = _ismiss_;
		this.iscrit = _iscrit_;
		this.isexcellent = _isexcellent_;
		this.islucky = _islucky_;
		this.attack = _attack_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ismiss);
		_os_.marshal(iscrit);
		_os_.marshal(isexcellent);
		_os_.marshal(islucky);
		_os_.marshal(attack);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ismiss = _os_.unmarshal_byte();
		iscrit = _os_.unmarshal_byte();
		isexcellent = _os_.unmarshal_byte();
		islucky = _os_.unmarshal_byte();
		attack = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BeAttackResult) {
			BeAttackResult _o_ = (BeAttackResult)_o1_;
			if (ismiss != _o_.ismiss) return false;
			if (iscrit != _o_.iscrit) return false;
			if (isexcellent != _o_.isexcellent) return false;
			if (islucky != _o_.islucky) return false;
			if (attack != _o_.attack) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)ismiss;
		_h_ += (int)iscrit;
		_h_ += (int)isexcellent;
		_h_ += (int)islucky;
		_h_ += attack;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ismiss).append(",");
		_sb_.append(iscrit).append(",");
		_sb_.append(isexcellent).append(",");
		_sb_.append(islucky).append(",");
		_sb_.append(attack).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(BeAttackResult _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ismiss - _o_.ismiss;
		if (0 != _c_) return _c_;
		_c_ = iscrit - _o_.iscrit;
		if (0 != _c_) return _c_;
		_c_ = isexcellent - _o_.isexcellent;
		if (0 != _c_) return _c_;
		_c_ = islucky - _o_.islucky;
		if (0 != _c_) return _c_;
		_c_ = attack - _o_.attack;
		if (0 != _c_) return _c_;
		return _c_;
	}

}


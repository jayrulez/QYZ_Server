
package lx.gs.role.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class CurrencyAddRecord implements Marshal , Comparable<CurrencyAddRecord>{
	public int ctype;
	public int add;

	public CurrencyAddRecord() {
	}

	public CurrencyAddRecord(int _ctype_, int _add_) {
		this.ctype = _ctype_;
		this.add = _add_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ctype);
		_os_.marshal(add);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ctype = _os_.unmarshal_int();
		add = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CurrencyAddRecord) {
			CurrencyAddRecord _o_ = (CurrencyAddRecord)_o1_;
			if (ctype != _o_.ctype) return false;
			if (add != _o_.add) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ctype;
		_h_ += add;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ctype).append(",");
		_sb_.append(add).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CurrencyAddRecord _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ctype - _o_.ctype;
		if (0 != _c_) return _c_;
		_c_ = add - _o_.add;
		if (0 != _c_) return _c_;
		return _c_;
	}

}


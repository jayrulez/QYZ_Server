
package lx.gs.dress;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Dress implements Marshal , Comparable<Dress>{
	public int dresskey; // 时装的key
	public long expiretime; // 过期时间

	public Dress() {
	}

	public Dress(int _dresskey_, long _expiretime_) {
		this.dresskey = _dresskey_;
		this.expiretime = _expiretime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(dresskey);
		_os_.marshal(expiretime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		dresskey = _os_.unmarshal_int();
		expiretime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Dress) {
			Dress _o_ = (Dress)_o1_;
			if (dresskey != _o_.dresskey) return false;
			if (expiretime != _o_.expiretime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += dresskey;
		_h_ += (int)expiretime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(dresskey).append(",");
		_sb_.append(expiretime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(Dress _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = dresskey - _o_.dresskey;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(expiretime - _o_.expiretime);
		if (0 != _c_) return _c_;
		return _c_;
	}

}


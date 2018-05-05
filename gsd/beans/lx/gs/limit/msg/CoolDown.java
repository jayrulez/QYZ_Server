
package lx.gs.limit.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class CoolDown implements Marshal , Comparable<CoolDown>{
	public long id;
	public long expiretime;

	public CoolDown() {
	}

	public CoolDown(long _id_, long _expiretime_) {
		this.id = _id_;
		this.expiretime = _expiretime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(expiretime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_long();
		expiretime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CoolDown) {
			CoolDown _o_ = (CoolDown)_o1_;
			if (id != _o_.id) return false;
			if (expiretime != _o_.expiretime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)id;
		_h_ += (int)expiretime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append(expiretime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CoolDown _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(id - _o_.id);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(expiretime - _o_.expiretime);
		if (0 != _c_) return _c_;
		return _c_;
	}

}


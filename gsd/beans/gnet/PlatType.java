
package gnet;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class PlatType implements Marshal , Comparable<PlatType>{
	public final static int TEST = 0;
	public final static int ONESDK = 1;

	public int plat;

	public PlatType() {
	}

	public PlatType(int _plat_) {
		this.plat = _plat_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(plat);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		plat = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlatType) {
			PlatType _o_ = (PlatType)_o1_;
			if (plat != _o_.plat) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += plat;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(plat).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(PlatType _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = plat - _o_.plat;
		if (0 != _c_) return _c_;
		return _c_;
	}

}


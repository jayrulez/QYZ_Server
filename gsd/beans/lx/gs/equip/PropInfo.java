
package lx.gs.equip;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class PropInfo implements Marshal {
	public int key;
	public float val;

	public PropInfo() {
	}

	public PropInfo(int _key_, float _val_) {
		this.key = _key_;
		this.val = _val_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(key);
		_os_.marshal(val);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		key = _os_.unmarshal_int();
		val = _os_.unmarshal_float();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PropInfo) {
			PropInfo _o_ = (PropInfo)_o1_;
			if (key != _o_.key) return false;
			if (val != _o_.val) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += key;
		_h_ += Float.floatToIntBits(val);
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(key).append(",");
		_sb_.append(val).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}



package lx.gs.equip;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 饰品类：戒指，项链，手镯
*/
public class AccessoryProp implements Marshal {
	public java.util.ArrayList<lx.gs.equip.PropInfo> mainprop; // 主属性,目前是2个
	public java.util.ArrayList<lx.gs.equip.PropInfo> extraprop; // 附加属性，目前是5个

	public AccessoryProp() {
		mainprop = new java.util.ArrayList<lx.gs.equip.PropInfo>();
		extraprop = new java.util.ArrayList<lx.gs.equip.PropInfo>();
	}

	public AccessoryProp(java.util.ArrayList<lx.gs.equip.PropInfo> _mainprop_, java.util.ArrayList<lx.gs.equip.PropInfo> _extraprop_) {
		this.mainprop = _mainprop_;
		this.extraprop = _extraprop_;
	}

	public final boolean _validator_() {
		for (lx.gs.equip.PropInfo _v_ : mainprop)
			if (!_v_._validator_()) return false;
		for (lx.gs.equip.PropInfo _v_ : extraprop)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(mainprop.size());
		for (lx.gs.equip.PropInfo _v_ : mainprop) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(extraprop.size());
		for (lx.gs.equip.PropInfo _v_ : extraprop) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.equip.PropInfo _v_ = new lx.gs.equip.PropInfo();
			_v_.unmarshal(_os_);
			mainprop.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.equip.PropInfo _v_ = new lx.gs.equip.PropInfo();
			_v_.unmarshal(_os_);
			extraprop.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AccessoryProp) {
			AccessoryProp _o_ = (AccessoryProp)_o1_;
			if (!mainprop.equals(_o_.mainprop)) return false;
			if (!extraprop.equals(_o_.extraprop)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += mainprop.hashCode();
		_h_ += extraprop.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mainprop).append(",");
		_sb_.append(extraprop).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


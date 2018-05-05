
package lx.gs.equip.accessory;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SApplyAccessoryWashResult__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SApplyAccessoryWashResult extends __SApplyAccessoryWashResult__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6567986;

	public int getType() {
		return 6567986;
	}

	public int bagtype; // 饰品所在包裹类型
	public int pos; // 饰品所在位置
	public int oldpropindex; // 原属性key
	public lx.gs.equip.PropInfo newprop; // 洗练后获得的属性

	public SApplyAccessoryWashResult() {
		newprop = new lx.gs.equip.PropInfo();
	}

	public SApplyAccessoryWashResult(int _bagtype_, int _pos_, int _oldpropindex_, lx.gs.equip.PropInfo _newprop_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.oldpropindex = _oldpropindex_;
		this.newprop = _newprop_;
	}

	public final boolean _validator_() {
		if (!newprop._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.marshal(oldpropindex);
		_os_.marshal(newprop);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		oldpropindex = _os_.unmarshal_int();
		newprop.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SApplyAccessoryWashResult) {
			SApplyAccessoryWashResult _o_ = (SApplyAccessoryWashResult)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (oldpropindex != _o_.oldpropindex) return false;
			if (!newprop.equals(_o_.newprop)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += oldpropindex;
		_h_ += newprop.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(oldpropindex).append(",");
		_sb_.append(newprop).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


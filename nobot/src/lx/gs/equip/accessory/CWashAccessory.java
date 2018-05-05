
package lx.gs.equip.accessory;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CWashAccessory__ extends xio.Protocol { }

/** 洗练饰品
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CWashAccessory extends __CWashAccessory__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583363;

	public int getType() {
		return 6583363;
	}

	public int bagtype; // 饰品所在包裹类型
	public int pos; // 饰品所在位置
	public int washpropindex; // 主饰品要替换的副属性的index
	public int materialpos; // 材料所在位置,只从存储包裹中找

	public CWashAccessory() {
	}

	public CWashAccessory(int _bagtype_, int _pos_, int _washpropindex_, int _materialpos_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.washpropindex = _washpropindex_;
		this.materialpos = _materialpos_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.marshal(washpropindex);
		_os_.marshal(materialpos);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		washpropindex = _os_.unmarshal_int();
		materialpos = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CWashAccessory) {
			CWashAccessory _o_ = (CWashAccessory)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (washpropindex != _o_.washpropindex) return false;
			if (materialpos != _o_.materialpos) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += washpropindex;
		_h_ += materialpos;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(washpropindex).append(",");
		_sb_.append(materialpos).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CWashAccessory _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		_c_ = washpropindex - _o_.washpropindex;
		if (0 != _c_) return _c_;
		_c_ = materialpos - _o_.materialpos;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


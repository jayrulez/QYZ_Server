
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SUnloadJewelry__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SUnloadJewelry extends lx.gs.jade.__SUnloadJewelry__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571736;

	public int getType() {
		return 6571736;
	}

	public int position; // 宝珠索引
	public int index; // 放置到包裹后的编号
	public lx.gs.jade.Jewelry jewelry; // 宝珠信息

	public SUnloadJewelry() {
		jewelry = new lx.gs.jade.Jewelry();
	}

	public SUnloadJewelry(int _position_, int _index_, lx.gs.jade.Jewelry _jewelry_) {
		this.position = _position_;
		this.index = _index_;
		this.jewelry = _jewelry_;
	}

	public final boolean _validator_() {
		if (!jewelry._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(position);
		_os_.marshal(index);
		_os_.marshal(jewelry);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		position = _os_.unmarshal_int();
		index = _os_.unmarshal_int();
		jewelry.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SUnloadJewelry) {
			SUnloadJewelry _o_ = (SUnloadJewelry)_o1_;
			if (position != _o_.position) return false;
			if (index != _o_.index) return false;
			if (!jewelry.equals(_o_.jewelry)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += position;
		_h_ += index;
		_h_ += jewelry.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(position).append(",");
		_sb_.append(index).append(",");
		_sb_.append(jewelry).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SUnloadJewelry _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = position - _o_.position;
		if (0 != _c_) return _c_;
		_c_ = index - _o_.index;
		if (0 != _c_) return _c_;
		_c_ = jewelry.compareTo(_o_.jewelry);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


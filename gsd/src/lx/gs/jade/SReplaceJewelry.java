
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SReplaceJewelry__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SReplaceJewelry extends lx.gs.jade.__SReplaceJewelry__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6584296;

	public int getType() {
		return 6584296;
	}

	public int result;
	public int index; // 宝珠在包裹中的编号
	public int position; // 宝珠玉佩上的位置
	public lx.gs.jade.Jewelry jewelry; // 宝珠信息

	public SReplaceJewelry() {
		jewelry = new lx.gs.jade.Jewelry();
	}

	public SReplaceJewelry(int _result_, int _index_, int _position_, lx.gs.jade.Jewelry _jewelry_) {
		this.result = _result_;
		this.index = _index_;
		this.position = _position_;
		this.jewelry = _jewelry_;
	}

	public final boolean _validator_() {
        return jewelry._validator_();
    }

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(result);
		_os_.marshal(index);
		_os_.marshal(position);
		_os_.marshal(jewelry);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		result = _os_.unmarshal_int();
		index = _os_.unmarshal_int();
		position = _os_.unmarshal_int();
		jewelry.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SReplaceJewelry) {
			SReplaceJewelry _o_ = (SReplaceJewelry)_o1_;
			if (result != _o_.result) return false;
			if (index != _o_.index) return false;
			if (position != _o_.position) return false;
            return jewelry.equals(_o_.jewelry);
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += result;
		_h_ += index;
		_h_ += position;
		_h_ += jewelry.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(result).append(",");
		_sb_.append(index).append(",");
		_sb_.append(position).append(",");
		_sb_.append(jewelry).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SReplaceJewelry _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = result - _o_.result;
		if (0 != _c_) return _c_;
		_c_ = index - _o_.index;
		if (0 != _c_) return _c_;
		_c_ = position - _o_.position;
		if (0 != _c_) return _c_;
		_c_ = jewelry.compareTo(_o_.jewelry);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


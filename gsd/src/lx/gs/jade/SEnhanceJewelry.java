
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEnhanceJewelry__ extends xio.Protocol { }

/** 宝珠升级
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEnhanceJewelry extends lx.gs.jade.__SEnhanceJewelry__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6580950;

	public int getType() {
		return 6580950;
	}

	public int position; // 要升级的装配上的宝珠位置
	public int index; // 要升级的背包里的宝珠位置
	public lx.gs.jade.Jewelry jewelry; // 要升级的装配上的宝珠
	public java.util.ArrayList<lx.gs.jade.Jewelry> jewelrybag; // 宝珠背包

	public SEnhanceJewelry() {
		jewelry = new lx.gs.jade.Jewelry();
		jewelrybag = new java.util.ArrayList<lx.gs.jade.Jewelry>();
	}

	public SEnhanceJewelry(int _position_, int _index_, lx.gs.jade.Jewelry _jewelry_, java.util.ArrayList<lx.gs.jade.Jewelry> _jewelrybag_) {
		this.position = _position_;
		this.index = _index_;
		this.jewelry = _jewelry_;
		this.jewelrybag = _jewelrybag_;
	}

	public final boolean _validator_() {
		if (!jewelry._validator_()) return false;
		for (lx.gs.jade.Jewelry _v_ : jewelrybag)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(position);
		_os_.marshal(index);
		_os_.marshal(jewelry);
		_os_.compact_uint32(jewelrybag.size());
		for (lx.gs.jade.Jewelry _v_ : jewelrybag) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		position = _os_.unmarshal_int();
		index = _os_.unmarshal_int();
		jewelry.unmarshal(_os_);
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.jade.Jewelry _v_ = new lx.gs.jade.Jewelry();
			_v_.unmarshal(_os_);
			jewelrybag.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEnhanceJewelry) {
			SEnhanceJewelry _o_ = (SEnhanceJewelry)_o1_;
			if (position != _o_.position) return false;
			if (index != _o_.index) return false;
			if (!jewelry.equals(_o_.jewelry)) return false;
			if (!jewelrybag.equals(_o_.jewelrybag)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += position;
		_h_ += index;
		_h_ += jewelry.hashCode();
		_h_ += jewelrybag.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(position).append(",");
		_sb_.append(index).append(",");
		_sb_.append(jewelry).append(",");
		_sb_.append(jewelrybag).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


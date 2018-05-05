
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SLoadJewelry__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SLoadJewelry extends lx.gs.jade.__SLoadJewelry__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581351;

	public int getType() {
		return 6581351;
	}

	public int index; // 宝珠在包裹中的编号
	public int position; // 宝珠玉佩上的位置
	public lx.gs.jade.Jewelry jewelry; // 宝珠信息
	public lx.gs.jade.Jewelry oldjewelry; // 宝珠信息

	public SLoadJewelry() {
		jewelry = new lx.gs.jade.Jewelry();
		oldjewelry = new lx.gs.jade.Jewelry();
	}

	public SLoadJewelry(int _index_, int _position_, lx.gs.jade.Jewelry _jewelry_, lx.gs.jade.Jewelry _oldjewelry_) {
		this.index = _index_;
		this.position = _position_;
		this.jewelry = _jewelry_;
		this.oldjewelry = _oldjewelry_;
	}

	public final boolean _validator_() {
		if (!jewelry._validator_()) return false;
		if (!oldjewelry._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(index);
		_os_.marshal(position);
		_os_.marshal(jewelry);
		_os_.marshal(oldjewelry);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		index = _os_.unmarshal_int();
		position = _os_.unmarshal_int();
		jewelry.unmarshal(_os_);
		oldjewelry.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SLoadJewelry) {
			SLoadJewelry _o_ = (SLoadJewelry)_o1_;
			if (index != _o_.index) return false;
			if (position != _o_.position) return false;
			if (!jewelry.equals(_o_.jewelry)) return false;
			if (!oldjewelry.equals(_o_.oldjewelry)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += index;
		_h_ += position;
		_h_ += jewelry.hashCode();
		_h_ += oldjewelry.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(index).append(",");
		_sb_.append(position).append(",");
		_sb_.append(jewelry).append(",");
		_sb_.append(oldjewelry).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SLoadJewelry _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = index - _o_.index;
		if (0 != _c_) return _c_;
		_c_ = position - _o_.position;
		if (0 != _c_) return _c_;
		_c_ = jewelry.compareTo(_o_.jewelry);
		if (0 != _c_) return _c_;
		_c_ = oldjewelry.compareTo(_o_.oldjewelry);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


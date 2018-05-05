
package lx.gs.jade;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEnhanceJewelry__ extends xio.Protocol { }

/** 宝珠升级
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEnhanceJewelry extends __CEnhanceJewelry__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564739;

	public int getType() {
		return 6564739;
	}

	public int position; // 要升级的装配上的宝珠位置
	public int index; // 要升级的背包里的宝珠索引
	public java.util.ArrayList<Integer> doglist;

	public CEnhanceJewelry() {
		doglist = new java.util.ArrayList<Integer>();
	}

	public CEnhanceJewelry(int _position_, int _index_, java.util.ArrayList<Integer> _doglist_) {
		this.position = _position_;
		this.index = _index_;
		this.doglist = _doglist_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(position);
		_os_.marshal(index);
		_os_.compact_uint32(doglist.size());
		for (Integer _v_ : doglist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		position = _os_.unmarshal_int();
		index = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			doglist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEnhanceJewelry) {
			CEnhanceJewelry _o_ = (CEnhanceJewelry)_o1_;
			if (position != _o_.position) return false;
			if (index != _o_.index) return false;
			if (!doglist.equals(_o_.doglist)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += position;
		_h_ += index;
		_h_ += doglist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(position).append(",");
		_sb_.append(index).append(",");
		_sb_.append(doglist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


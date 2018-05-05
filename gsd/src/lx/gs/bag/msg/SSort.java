
package lx.gs.bag.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SSort__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SSort extends __SSort__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565195;

	public int getType() {
		return 6565195;
	}

	public int bagtype;
	public java.util.HashMap<Integer,Integer> itempos; // key = 旧位置， value = 新位置

	public SSort() {
		itempos = new java.util.HashMap<Integer,Integer>();
	}

	public SSort(int _bagtype_, java.util.HashMap<Integer,Integer> _itempos_) {
		this.bagtype = _bagtype_;
		this.itempos = _itempos_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.compact_uint32(itempos.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : itempos.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			itempos.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SSort) {
			SSort _o_ = (SSort)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (!itempos.equals(_o_.itempos)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += itempos.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(itempos).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


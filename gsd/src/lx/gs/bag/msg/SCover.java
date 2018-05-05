
package lx.gs.bag.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SCover__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SCover extends __SCover__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577700;

	public int getType() {
		return 6577700;
	}

	public int bagtype;
	public java.util.HashMap<Integer,com.goldhuman.Common.Octets> iteminfo; // key是位置, value是物品信息，空则没有物品，反序列化结构在具体模块中

	public SCover() {
		iteminfo = new java.util.HashMap<Integer,com.goldhuman.Common.Octets>();
	}

	public SCover(int _bagtype_, java.util.HashMap<Integer,com.goldhuman.Common.Octets> _iteminfo_) {
		this.bagtype = _bagtype_;
		this.iteminfo = _iteminfo_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.compact_uint32(iteminfo.size());
		for (java.util.Map.Entry<Integer, com.goldhuman.Common.Octets> _e_ : iteminfo.entrySet()) {
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
			com.goldhuman.Common.Octets _v_;
			_v_ = _os_.unmarshal_Octets();
			iteminfo.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SCover) {
			SCover _o_ = (SCover)_o1_;
			if (bagtype != _o_.bagtype) return false;
            return iteminfo.equals(_o_.iteminfo);
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += iteminfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(iteminfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


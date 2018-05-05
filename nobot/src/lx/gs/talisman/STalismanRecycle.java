
package lx.gs.talisman;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __STalismanRecycle__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class STalismanRecycle extends __STalismanRecycle__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568193;

	public int getType() {
		return 6568193;
	}

	public int bagtype;
	public int pos;
	public java.util.HashMap<Integer,Integer> expitems; // 经验丹itemkey-nums
	public java.util.HashMap<Integer,Integer> staritems; // 星阶丹和原始法宝

	public STalismanRecycle() {
		expitems = new java.util.HashMap<Integer,Integer>();
		staritems = new java.util.HashMap<Integer,Integer>();
	}

	public STalismanRecycle(int _bagtype_, int _pos_, java.util.HashMap<Integer,Integer> _expitems_, java.util.HashMap<Integer,Integer> _staritems_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.expitems = _expitems_;
		this.staritems = _staritems_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.compact_uint32(expitems.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : expitems.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(staritems.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : staritems.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			expitems.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			staritems.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof STalismanRecycle) {
			STalismanRecycle _o_ = (STalismanRecycle)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (!expitems.equals(_o_.expitems)) return false;
			if (!staritems.equals(_o_.staritems)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += expitems.hashCode();
		_h_ += staritems.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(expitems).append(",");
		_sb_.append(staritems).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


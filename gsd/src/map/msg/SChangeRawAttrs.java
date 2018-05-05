
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SChangeRawAttrs__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SChangeRawAttrs extends __SChangeRawAttrs__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6696702;

	public int getType() {
		return 6696702;
	}

	public int resethpmp;
	public int combatpower;
	public java.util.ArrayList<Float> attrs;

	public SChangeRawAttrs() {
		attrs = new java.util.ArrayList<Float>();
	}

	public SChangeRawAttrs(int _resethpmp_, int _combatpower_, java.util.ArrayList<Float> _attrs_) {
		this.resethpmp = _resethpmp_;
		this.combatpower = _combatpower_;
		this.attrs = _attrs_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(resethpmp);
		_os_.marshal(combatpower);
		_os_.compact_uint32(attrs.size());
		for (Float _v_ : attrs) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		resethpmp = _os_.unmarshal_int();
		combatpower = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			float _v_;
			_v_ = _os_.unmarshal_float();
			attrs.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SChangeRawAttrs) {
			SChangeRawAttrs _o_ = (SChangeRawAttrs)_o1_;
			if (resethpmp != _o_.resethpmp) return false;
			if (combatpower != _o_.combatpower) return false;
			if (!attrs.equals(_o_.attrs)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += resethpmp;
		_h_ += combatpower;
		_h_ += attrs.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(resethpmp).append(",");
		_sb_.append(combatpower).append(",");
		_sb_.append(attrs).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


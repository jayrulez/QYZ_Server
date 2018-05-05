
package lx.gs.achievement.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SCounterChange__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SCounterChange extends __SCounterChange__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571901;

	public int getType() {
		return 6571901;
	}

	public int countertype;
	public long value;

	public SCounterChange() {
	}

	public SCounterChange(int _countertype_, long _value_) {
		this.countertype = _countertype_;
		this.value = _value_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(countertype);
		_os_.marshal(value);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		countertype = _os_.unmarshal_int();
		value = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SCounterChange) {
			SCounterChange _o_ = (SCounterChange)_o1_;
			if (countertype != _o_.countertype) return false;
			if (value != _o_.value) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += countertype;
		_h_ += (int)value;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(countertype).append(",");
		_sb_.append(value).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SCounterChange _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = countertype - _o_.countertype;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(value - _o_.value);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


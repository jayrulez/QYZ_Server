
package lx.gs.role.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SCurrencyAlert__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SCurrencyAlert extends __SCurrencyAlert__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564408;

	public int getType() {
		return 6564408;
	}

	public java.util.ArrayList<lx.gs.role.msg.CurrencyAddRecord> currencys;

	public SCurrencyAlert() {
		currencys = new java.util.ArrayList<lx.gs.role.msg.CurrencyAddRecord>();
	}

	public SCurrencyAlert(java.util.ArrayList<lx.gs.role.msg.CurrencyAddRecord> _currencys_) {
		this.currencys = _currencys_;
	}

	public final boolean _validator_() {
		for (lx.gs.role.msg.CurrencyAddRecord _v_ : currencys)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(currencys.size());
		for (lx.gs.role.msg.CurrencyAddRecord _v_ : currencys) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.role.msg.CurrencyAddRecord _v_ = new lx.gs.role.msg.CurrencyAddRecord();
			_v_.unmarshal(_os_);
			currencys.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SCurrencyAlert) {
			SCurrencyAlert _o_ = (SCurrencyAlert)_o1_;
			if (!currencys.equals(_o_.currencys)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += currencys.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(currencys).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


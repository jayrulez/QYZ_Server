
package lx.gs.role.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SKillMonster__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SKillMonster extends __SKillMonster__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578817;

	public int getType() {
		return 6578817;
	}

	public long todaytotaladdmonsterexp;
	public java.util.ArrayList<lx.gs.role.msg.PetExp> petexps;
	public java.util.ArrayList<lx.gs.role.msg.CurrencyAddRecord> currencys;

	public SKillMonster() {
		petexps = new java.util.ArrayList<lx.gs.role.msg.PetExp>();
		currencys = new java.util.ArrayList<lx.gs.role.msg.CurrencyAddRecord>();
	}

	public SKillMonster(long _todaytotaladdmonsterexp_, java.util.ArrayList<lx.gs.role.msg.PetExp> _petexps_, java.util.ArrayList<lx.gs.role.msg.CurrencyAddRecord> _currencys_) {
		this.todaytotaladdmonsterexp = _todaytotaladdmonsterexp_;
		this.petexps = _petexps_;
		this.currencys = _currencys_;
	}

	public final boolean _validator_() {
		for (lx.gs.role.msg.PetExp _v_ : petexps)
			if (!_v_._validator_()) return false;
		for (lx.gs.role.msg.CurrencyAddRecord _v_ : currencys)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(todaytotaladdmonsterexp);
		_os_.compact_uint32(petexps.size());
		for (lx.gs.role.msg.PetExp _v_ : petexps) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(currencys.size());
		for (lx.gs.role.msg.CurrencyAddRecord _v_ : currencys) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		todaytotaladdmonsterexp = _os_.unmarshal_long();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.role.msg.PetExp _v_ = new lx.gs.role.msg.PetExp();
			_v_.unmarshal(_os_);
			petexps.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.role.msg.CurrencyAddRecord _v_ = new lx.gs.role.msg.CurrencyAddRecord();
			_v_.unmarshal(_os_);
			currencys.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SKillMonster) {
			SKillMonster _o_ = (SKillMonster)_o1_;
			if (todaytotaladdmonsterexp != _o_.todaytotaladdmonsterexp) return false;
			if (!petexps.equals(_o_.petexps)) return false;
			if (!currencys.equals(_o_.currencys)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)todaytotaladdmonsterexp;
		_h_ += petexps.hashCode();
		_h_ += currencys.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(todaytotaladdmonsterexp).append(",");
		_sb_.append(petexps).append(",");
		_sb_.append(currencys).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


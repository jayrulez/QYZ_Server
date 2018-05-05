
package lx.gs.limit.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SLimit__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SLimit extends __SLimit__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556300;

	public int getType() {
		return 6556300;
	}

	public java.util.ArrayList<lx.gs.limit.msg.Limit> limits;
	public java.util.ArrayList<lx.gs.limit.msg.CoolDown> cooldowns;

	public SLimit() {
		limits = new java.util.ArrayList<lx.gs.limit.msg.Limit>();
		cooldowns = new java.util.ArrayList<lx.gs.limit.msg.CoolDown>();
	}

	public SLimit(java.util.ArrayList<lx.gs.limit.msg.Limit> _limits_, java.util.ArrayList<lx.gs.limit.msg.CoolDown> _cooldowns_) {
		this.limits = _limits_;
		this.cooldowns = _cooldowns_;
	}

	public final boolean _validator_() {
		for (lx.gs.limit.msg.Limit _v_ : limits)
			if (!_v_._validator_()) return false;
		for (lx.gs.limit.msg.CoolDown _v_ : cooldowns)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(limits.size());
		for (lx.gs.limit.msg.Limit _v_ : limits) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(cooldowns.size());
		for (lx.gs.limit.msg.CoolDown _v_ : cooldowns) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.limit.msg.Limit _v_ = new lx.gs.limit.msg.Limit();
			_v_.unmarshal(_os_);
			limits.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.limit.msg.CoolDown _v_ = new lx.gs.limit.msg.CoolDown();
			_v_.unmarshal(_os_);
			cooldowns.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SLimit) {
			SLimit _o_ = (SLimit)_o1_;
			if (!limits.equals(_o_.limits)) return false;
			if (!cooldowns.equals(_o_.cooldowns)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += limits.hashCode();
		_h_ += cooldowns.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(limits).append(",");
		_sb_.append(cooldowns).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


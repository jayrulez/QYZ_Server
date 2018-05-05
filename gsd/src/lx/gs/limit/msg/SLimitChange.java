
package lx.gs.limit.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SLimitChange__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SLimitChange extends __SLimitChange__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556301;

	public int getType() {
		return 6556301;
	}

	public java.util.ArrayList<lx.gs.limit.msg.Limit> changelimits;
	public java.util.ArrayList<Long> removelimits;

	public SLimitChange() {
		changelimits = new java.util.ArrayList<lx.gs.limit.msg.Limit>();
		removelimits = new java.util.ArrayList<Long>();
	}

	public SLimitChange(java.util.ArrayList<lx.gs.limit.msg.Limit> _changelimits_, java.util.ArrayList<Long> _removelimits_) {
		this.changelimits = _changelimits_;
		this.removelimits = _removelimits_;
	}

	public final boolean _validator_() {
		for (lx.gs.limit.msg.Limit _v_ : changelimits)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(changelimits.size());
		for (lx.gs.limit.msg.Limit _v_ : changelimits) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(removelimits.size());
		for (Long _v_ : removelimits) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.limit.msg.Limit _v_ = new lx.gs.limit.msg.Limit();
			_v_.unmarshal(_os_);
			changelimits.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			removelimits.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SLimitChange) {
			SLimitChange _o_ = (SLimitChange)_o1_;
			if (!changelimits.equals(_o_.changelimits)) return false;
			if (!removelimits.equals(_o_.removelimits)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += changelimits.hashCode();
		_h_ += removelimits.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(changelimits).append(",");
		_sb_.append(removelimits).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


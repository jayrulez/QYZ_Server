
package lx.gs.exchange.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import lx.gs.exchange.PDelItem;

import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CDelItem__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CDelItem extends __CDelItem__ {
	@Override
	protected void process() {
		new PDelItem(this).validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557604;

	public int getType() {
		return 6557604;
	}

	public long exchangeid;

	public CDelItem() {
	}

	public CDelItem(long _exchangeid_) {
		this.exchangeid = _exchangeid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(exchangeid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		exchangeid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CDelItem) {
			CDelItem _o_ = (CDelItem)_o1_;
			if (exchangeid != _o_.exchangeid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)exchangeid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(exchangeid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CDelItem _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(exchangeid - _o_.exchangeid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


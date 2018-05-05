
package lx.gs.exchange.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBuyByOther__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBuyByOther extends __SBuyByOther__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557612;

	public int getType() {
		return 6557612;
	}

	public long exchangeid;
	public int remainnum;

	public SBuyByOther() {
	}

	public SBuyByOther(long _exchangeid_, int _remainnum_) {
		this.exchangeid = _exchangeid_;
		this.remainnum = _remainnum_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(exchangeid);
		_os_.marshal(remainnum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		exchangeid = _os_.unmarshal_long();
		remainnum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBuyByOther) {
			SBuyByOther _o_ = (SBuyByOther)_o1_;
			if (exchangeid != _o_.exchangeid) return false;
			if (remainnum != _o_.remainnum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)exchangeid;
		_h_ += remainnum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(exchangeid).append(",");
		_sb_.append(remainnum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SBuyByOther _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(exchangeid - _o_.exchangeid);
		if (0 != _c_) return _c_;
		_c_ = remainnum - _o_.remainnum;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}



package lx.gs.dress;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBuyDress__ extends xio.Protocol { }

/** 购买dress，购买后处于获得状态
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBuyDress extends lx.gs.dress.__SBuyDress__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577189;

	public int getType() {
		return 6577189;
	}

	public int dresskey; // 购买的Dresskey

	public SBuyDress() {
	}

	public SBuyDress(int _dresskey_) {
		this.dresskey = _dresskey_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(dresskey);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		dresskey = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBuyDress) {
			SBuyDress _o_ = (SBuyDress)_o1_;
			if (dresskey != _o_.dresskey) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += dresskey;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(dresskey).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SBuyDress _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = dresskey - _o_.dresskey;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}



package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGodAnimalActivityStartNotify__ extends xio.Protocol { }

/** 神兽挑战开始通知
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGodAnimalActivityStartNotify extends __SGodAnimalActivityStartNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6569459;

	public int getType() {
		return 6569459;
	}

	public long countdowntime;

	public SGodAnimalActivityStartNotify() {
	}

	public SGodAnimalActivityStartNotify(long _countdowntime_) {
		this.countdowntime = _countdowntime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(countdowntime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		countdowntime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGodAnimalActivityStartNotify) {
			SGodAnimalActivityStartNotify _o_ = (SGodAnimalActivityStartNotify)_o1_;
			if (countdowntime != _o_.countdowntime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)countdowntime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(countdowntime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SGodAnimalActivityStartNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(countdowntime - _o_.countdowntime);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


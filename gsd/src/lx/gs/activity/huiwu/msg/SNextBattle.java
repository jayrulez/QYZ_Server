
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SNextBattle__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SNextBattle extends __SNextBattle__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586065;

	public int getType() {
		return 6586065;
	}

	public int round;

	public SNextBattle() {
	}

	public SNextBattle(int _round_) {
		this.round = _round_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(round);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		round = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SNextBattle) {
			SNextBattle _o_ = (SNextBattle)_o1_;
            return round == _o_.round;
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += round;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(round).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SNextBattle _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = round - _o_.round;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


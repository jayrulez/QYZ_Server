
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SRoundStage__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SRoundStage extends __SRoundStage__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571714;

	public int getType() {
		return 6571714;
	}

	public int termid;
	public int round;
	public int roundstage;

	public SRoundStage() {
	}

	public SRoundStage(int _termid_, int _round_, int _roundstage_) {
		this.termid = _termid_;
		this.round = _round_;
		this.roundstage = _roundstage_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(termid);
		_os_.marshal(round);
		_os_.marshal(roundstage);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		termid = _os_.unmarshal_int();
		round = _os_.unmarshal_int();
		roundstage = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SRoundStage) {
			SRoundStage _o_ = (SRoundStage)_o1_;
			if (termid != _o_.termid) return false;
			if (round != _o_.round) return false;
			if (roundstage != _o_.roundstage) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += termid;
		_h_ += round;
		_h_ += roundstage;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(termid).append(",");
		_sb_.append(round).append(",");
		_sb_.append(roundstage).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SRoundStage _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = termid - _o_.termid;
		if (0 != _c_) return _c_;
		_c_ = round - _o_.round;
		if (0 != _c_) return _c_;
		_c_ = roundstage - _o_.roundstage;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


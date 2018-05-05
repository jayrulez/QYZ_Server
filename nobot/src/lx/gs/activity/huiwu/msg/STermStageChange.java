
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __STermStageChange__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class STermStageChange extends __STermStageChange__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582120;

	public int getType() {
		return 6582120;
	}

	public int termid;
	public int stage;

	public STermStageChange() {
	}

	public STermStageChange(int _termid_, int _stage_) {
		this.termid = _termid_;
		this.stage = _stage_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(termid);
		_os_.marshal(stage);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		termid = _os_.unmarshal_int();
		stage = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof STermStageChange) {
			STermStageChange _o_ = (STermStageChange)_o1_;
			if (termid != _o_.termid) return false;
			if (stage != _o_.stage) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += termid;
		_h_ += stage;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(termid).append(",");
		_sb_.append(stage).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(STermStageChange _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = termid - _o_.termid;
		if (0 != _c_) return _c_;
		_c_ = stage - _o_.stage;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


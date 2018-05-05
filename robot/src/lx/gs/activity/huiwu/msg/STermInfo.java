
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __STermInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class STermInfo extends __STermInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582808;

	public int getType() {
		return 6582808;
	}

	public int termid;
	public int stage;
	public int roundindex;
	public int roundstage;
	public long opentime;
	public long guessendtime;
	public long guessroleid; // 猜测冠军玩家
	public int hasenroll; // 是否报名

	public STermInfo() {
	}

	public STermInfo(int _termid_, int _stage_, int _roundindex_, int _roundstage_, long _opentime_, long _guessendtime_, long _guessroleid_, int _hasenroll_) {
		this.termid = _termid_;
		this.stage = _stage_;
		this.roundindex = _roundindex_;
		this.roundstage = _roundstage_;
		this.opentime = _opentime_;
		this.guessendtime = _guessendtime_;
		this.guessroleid = _guessroleid_;
		this.hasenroll = _hasenroll_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(termid);
		_os_.marshal(stage);
		_os_.marshal(roundindex);
		_os_.marshal(roundstage);
		_os_.marshal(opentime);
		_os_.marshal(guessendtime);
		_os_.marshal(guessroleid);
		_os_.marshal(hasenroll);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		termid = _os_.unmarshal_int();
		stage = _os_.unmarshal_int();
		roundindex = _os_.unmarshal_int();
		roundstage = _os_.unmarshal_int();
		opentime = _os_.unmarshal_long();
		guessendtime = _os_.unmarshal_long();
		guessroleid = _os_.unmarshal_long();
		hasenroll = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof STermInfo) {
			STermInfo _o_ = (STermInfo)_o1_;
			if (termid != _o_.termid) return false;
			if (stage != _o_.stage) return false;
			if (roundindex != _o_.roundindex) return false;
			if (roundstage != _o_.roundstage) return false;
			if (opentime != _o_.opentime) return false;
			if (guessendtime != _o_.guessendtime) return false;
			if (guessroleid != _o_.guessroleid) return false;
			if (hasenroll != _o_.hasenroll) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += termid;
		_h_ += stage;
		_h_ += roundindex;
		_h_ += roundstage;
		_h_ += (int)opentime;
		_h_ += (int)guessendtime;
		_h_ += (int)guessroleid;
		_h_ += hasenroll;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(termid).append(",");
		_sb_.append(stage).append(",");
		_sb_.append(roundindex).append(",");
		_sb_.append(roundstage).append(",");
		_sb_.append(opentime).append(",");
		_sb_.append(guessendtime).append(",");
		_sb_.append(guessroleid).append(",");
		_sb_.append(hasenroll).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(STermInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = termid - _o_.termid;
		if (0 != _c_) return _c_;
		_c_ = stage - _o_.stage;
		if (0 != _c_) return _c_;
		_c_ = roundindex - _o_.roundindex;
		if (0 != _c_) return _c_;
		_c_ = roundstage - _o_.roundstage;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(opentime - _o_.opentime);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(guessendtime - _o_.guessendtime);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(guessroleid - _o_.guessroleid);
		if (0 != _c_) return _c_;
		_c_ = hasenroll - _o_.hasenroll;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


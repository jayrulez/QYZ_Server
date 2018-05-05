
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SCurTeamInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SCurTeamInfo extends __SCurTeamInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6563870;

	public int getType() {
		return 6563870;
	}

	public int termid;
	public int stage;
	public long opentime;
	public long guessroleid; // 猜测冠军玩家
	public int hasenroll; // 是否报名
	public int needattendnextbattle;
	public long battlebegintime;

	public SCurTeamInfo() {
	}

	public SCurTeamInfo(int _termid_, int _stage_, long _opentime_, long _guessroleid_, int _hasenroll_, int _needattendnextbattle_, long _battlebegintime_) {
		this.termid = _termid_;
		this.stage = _stage_;
		this.opentime = _opentime_;
		this.guessroleid = _guessroleid_;
		this.hasenroll = _hasenroll_;
		this.needattendnextbattle = _needattendnextbattle_;
		this.battlebegintime = _battlebegintime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(termid);
		_os_.marshal(stage);
		_os_.marshal(opentime);
		_os_.marshal(guessroleid);
		_os_.marshal(hasenroll);
		_os_.marshal(needattendnextbattle);
		_os_.marshal(battlebegintime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		termid = _os_.unmarshal_int();
		stage = _os_.unmarshal_int();
		opentime = _os_.unmarshal_long();
		guessroleid = _os_.unmarshal_long();
		hasenroll = _os_.unmarshal_int();
		needattendnextbattle = _os_.unmarshal_int();
		battlebegintime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SCurTeamInfo) {
			SCurTeamInfo _o_ = (SCurTeamInfo)_o1_;
			if (termid != _o_.termid) return false;
			if (stage != _o_.stage) return false;
			if (opentime != _o_.opentime) return false;
			if (guessroleid != _o_.guessroleid) return false;
			if (hasenroll != _o_.hasenroll) return false;
			if (needattendnextbattle != _o_.needattendnextbattle) return false;
            return battlebegintime == _o_.battlebegintime;
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += termid;
		_h_ += stage;
		_h_ += (int)opentime;
		_h_ += (int)guessroleid;
		_h_ += hasenroll;
		_h_ += needattendnextbattle;
		_h_ += (int)battlebegintime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(termid).append(",");
		_sb_.append(stage).append(",");
		_sb_.append(opentime).append(",");
		_sb_.append(guessroleid).append(",");
		_sb_.append(hasenroll).append(",");
		_sb_.append(needattendnextbattle).append(",");
		_sb_.append(battlebegintime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SCurTeamInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = termid - _o_.termid;
		if (0 != _c_) return _c_;
		_c_ = stage - _o_.stage;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(opentime - _o_.opentime);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(guessroleid - _o_.guessroleid);
		if (0 != _c_) return _c_;
		_c_ = hasenroll - _o_.hasenroll;
		if (0 != _c_) return _c_;
		_c_ = needattendnextbattle - _o_.needattendnextbattle;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(battlebegintime - _o_.battlebegintime);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


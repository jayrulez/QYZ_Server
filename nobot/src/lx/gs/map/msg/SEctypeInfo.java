
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEctypeInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEctypeInfo extends __SEctypeInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582946;

	public int getType() {
		return 6582946;
	}

	public java.util.HashMap<Integer,lx.gs.map.msg.ClimbTowerInfo> climbtowers;
	public java.util.HashMap<Integer,lx.gs.map.msg.ChapterInfo> chapters;
	public java.util.HashMap<Integer,lx.gs.map.msg.DailyInfo> dailys; // key -> ectypetype
	public lx.gs.map.msg.TeamFightInfo teamfight;
	public java.util.HashMap<Integer,lx.gs.map.msg.MultiStoryInfo> multistory;
	public int matchtype; // cfg.ectype.MatchType
	public long nextmatchtime; // 允许参与下一次匹配的时间

	public SEctypeInfo() {
		climbtowers = new java.util.HashMap<Integer,lx.gs.map.msg.ClimbTowerInfo>();
		chapters = new java.util.HashMap<Integer,lx.gs.map.msg.ChapterInfo>();
		dailys = new java.util.HashMap<Integer,lx.gs.map.msg.DailyInfo>();
		teamfight = new lx.gs.map.msg.TeamFightInfo();
		multistory = new java.util.HashMap<Integer,lx.gs.map.msg.MultiStoryInfo>();
	}

	public SEctypeInfo(java.util.HashMap<Integer,lx.gs.map.msg.ClimbTowerInfo> _climbtowers_, java.util.HashMap<Integer,lx.gs.map.msg.ChapterInfo> _chapters_, java.util.HashMap<Integer,lx.gs.map.msg.DailyInfo> _dailys_, lx.gs.map.msg.TeamFightInfo _teamfight_, java.util.HashMap<Integer,lx.gs.map.msg.MultiStoryInfo> _multistory_, int _matchtype_, long _nextmatchtime_) {
		this.climbtowers = _climbtowers_;
		this.chapters = _chapters_;
		this.dailys = _dailys_;
		this.teamfight = _teamfight_;
		this.multistory = _multistory_;
		this.matchtype = _matchtype_;
		this.nextmatchtime = _nextmatchtime_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.map.msg.ClimbTowerInfo> _e_ : climbtowers.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		for (java.util.Map.Entry<Integer, lx.gs.map.msg.ChapterInfo> _e_ : chapters.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		for (java.util.Map.Entry<Integer, lx.gs.map.msg.DailyInfo> _e_ : dailys.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		if (!teamfight._validator_()) return false;
		for (java.util.Map.Entry<Integer, lx.gs.map.msg.MultiStoryInfo> _e_ : multistory.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(climbtowers.size());
		for (java.util.Map.Entry<Integer, lx.gs.map.msg.ClimbTowerInfo> _e_ : climbtowers.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(chapters.size());
		for (java.util.Map.Entry<Integer, lx.gs.map.msg.ChapterInfo> _e_ : chapters.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(dailys.size());
		for (java.util.Map.Entry<Integer, lx.gs.map.msg.DailyInfo> _e_ : dailys.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(teamfight);
		_os_.compact_uint32(multistory.size());
		for (java.util.Map.Entry<Integer, lx.gs.map.msg.MultiStoryInfo> _e_ : multistory.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(matchtype);
		_os_.marshal(nextmatchtime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.map.msg.ClimbTowerInfo _v_ = new lx.gs.map.msg.ClimbTowerInfo();
			_v_.unmarshal(_os_);
			climbtowers.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.map.msg.ChapterInfo _v_ = new lx.gs.map.msg.ChapterInfo();
			_v_.unmarshal(_os_);
			chapters.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.map.msg.DailyInfo _v_ = new lx.gs.map.msg.DailyInfo();
			_v_.unmarshal(_os_);
			dailys.put(_k_, _v_);
		}
		teamfight.unmarshal(_os_);
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.map.msg.MultiStoryInfo _v_ = new lx.gs.map.msg.MultiStoryInfo();
			_v_.unmarshal(_os_);
			multistory.put(_k_, _v_);
		}
		matchtype = _os_.unmarshal_int();
		nextmatchtime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEctypeInfo) {
			SEctypeInfo _o_ = (SEctypeInfo)_o1_;
			if (!climbtowers.equals(_o_.climbtowers)) return false;
			if (!chapters.equals(_o_.chapters)) return false;
			if (!dailys.equals(_o_.dailys)) return false;
			if (!teamfight.equals(_o_.teamfight)) return false;
			if (!multistory.equals(_o_.multistory)) return false;
			if (matchtype != _o_.matchtype) return false;
			if (nextmatchtime != _o_.nextmatchtime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += climbtowers.hashCode();
		_h_ += chapters.hashCode();
		_h_ += dailys.hashCode();
		_h_ += teamfight.hashCode();
		_h_ += multistory.hashCode();
		_h_ += matchtype;
		_h_ += (int)nextmatchtime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(climbtowers).append(",");
		_sb_.append(chapters).append(",");
		_sb_.append(dailys).append(",");
		_sb_.append(teamfight).append(",");
		_sb_.append(multistory).append(",");
		_sb_.append(matchtype).append(",");
		_sb_.append(nextmatchtime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


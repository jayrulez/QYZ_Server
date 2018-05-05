
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __STeamSpeedTimeInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class STeamSpeedTimeInfo extends __STeamSpeedTimeInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583341;

	public int getType() {
		return 6583341;
	}

	public java.util.ArrayList<lx.gs.map.msg.TimeRange> opentime; // 开放时间
	public int applytime; // 报名时间
	public int competitiontime; // 比赛持续时间

	public STeamSpeedTimeInfo() {
		opentime = new java.util.ArrayList<lx.gs.map.msg.TimeRange>();
	}

	public STeamSpeedTimeInfo(java.util.ArrayList<lx.gs.map.msg.TimeRange> _opentime_, int _applytime_, int _competitiontime_) {
		this.opentime = _opentime_;
		this.applytime = _applytime_;
		this.competitiontime = _competitiontime_;
	}

	public final boolean _validator_() {
		for (lx.gs.map.msg.TimeRange _v_ : opentime)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(opentime.size());
		for (lx.gs.map.msg.TimeRange _v_ : opentime) {
			_os_.marshal(_v_);
		}
		_os_.marshal(applytime);
		_os_.marshal(competitiontime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.map.msg.TimeRange _v_ = new lx.gs.map.msg.TimeRange();
			_v_.unmarshal(_os_);
			opentime.add(_v_);
		}
		applytime = _os_.unmarshal_int();
		competitiontime = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof STeamSpeedTimeInfo) {
			STeamSpeedTimeInfo _o_ = (STeamSpeedTimeInfo)_o1_;
			if (!opentime.equals(_o_.opentime)) return false;
			if (applytime != _o_.applytime) return false;
			if (competitiontime != _o_.competitiontime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += opentime.hashCode();
		_h_ += applytime;
		_h_ += competitiontime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(opentime).append(",");
		_sb_.append(applytime).append(",");
		_sb_.append(competitiontime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


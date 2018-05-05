
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SMatchTeamFightSucc__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SMatchTeamFightSucc extends __SMatchTeamFightSucc__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583040;

	public int getType() {
		return 6583040;
	}

	public int countdown; // 倒计时
	public lx.gs.map.msg.MatchTeamInfo team1;
	public lx.gs.map.msg.MatchTeamInfo team2;

	public SMatchTeamFightSucc() {
		team1 = new lx.gs.map.msg.MatchTeamInfo();
		team2 = new lx.gs.map.msg.MatchTeamInfo();
	}

	public SMatchTeamFightSucc(int _countdown_, lx.gs.map.msg.MatchTeamInfo _team1_, lx.gs.map.msg.MatchTeamInfo _team2_) {
		this.countdown = _countdown_;
		this.team1 = _team1_;
		this.team2 = _team2_;
	}

	public final boolean _validator_() {
		if (!team1._validator_()) return false;
		if (!team2._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(countdown);
		_os_.marshal(team1);
		_os_.marshal(team2);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		countdown = _os_.unmarshal_int();
		team1.unmarshal(_os_);
		team2.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SMatchTeamFightSucc) {
			SMatchTeamFightSucc _o_ = (SMatchTeamFightSucc)_o1_;
			if (countdown != _o_.countdown) return false;
			if (!team1.equals(_o_.team1)) return false;
			if (!team2.equals(_o_.team2)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += countdown;
		_h_ += team1.hashCode();
		_h_ += team2.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(countdown).append(",");
		_sb_.append(team1).append(",");
		_sb_.append(team2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}



package lx.matcher;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import teamfight.TeamFightMatcher;
import teamfight.TeamFightModule;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __GAddTeamFightMatch__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class GAddTeamFightMatch extends __GAddTeamFightMatch__ {
	@Override
	protected void process() {
        TeamFightModule.matcher.add(TeamFightModule.matcher.getMatcher().createTeam(gid, viplevel, team.members));
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6639576;

	public int getType() {
		return 6639576;
	}

	public long gid;
	public int viplevel;
	public lx.gs.map.msg.MatchTeamInfo team;

	public GAddTeamFightMatch() {
		team = new lx.gs.map.msg.MatchTeamInfo();
	}

	public GAddTeamFightMatch(long _gid_, int _viplevel_, lx.gs.map.msg.MatchTeamInfo _team_) {
		this.gid = _gid_;
		this.viplevel = _viplevel_;
		this.team = _team_;
	}

	public final boolean _validator_() {
		if (!team._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(gid);
		_os_.marshal(viplevel);
		_os_.marshal(team);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		gid = _os_.unmarshal_long();
		viplevel = _os_.unmarshal_int();
		team.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GAddTeamFightMatch) {
			GAddTeamFightMatch _o_ = (GAddTeamFightMatch)_o1_;
			if (gid != _o_.gid) return false;
			if (viplevel != _o_.viplevel) return false;
			if (!team.equals(_o_.team)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)gid;
		_h_ += viplevel;
		_h_ += team.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(gid).append(",");
		_sb_.append(viplevel).append(",");
		_sb_.append(team).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


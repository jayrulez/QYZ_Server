
package lx.matcher.guardtower;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import guardtower.GuardTowerMatcher;
import match.Member;

import java.util.ArrayList;
import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __GAddGuardTowerMatch__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class GAddGuardTowerMatch extends __GAddGuardTowerMatch__ {
	@Override
	protected void process() {
		List<Member> members = new ArrayList<>();
		team.members.forEach(roleShowInfo4 ->
				members.add(new GuardTowerMatcher.GuardTowerMember(roleShowInfo4.roleid, roleShowInfo4)));
		guardtower.GuardTowerModule.matcher.add(new GuardTowerMatcher.ETeam(gid, members, selfpower, minpower));
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6637026;

	public int getType() {
		return 6637026;
	}

	public long gid;
	public int minpower;
	public int selfpower;
	public lx.gs.map.msg.MatchTeamInfo team;

	public GAddGuardTowerMatch() {
		team = new lx.gs.map.msg.MatchTeamInfo();
	}

	public GAddGuardTowerMatch(long _gid_, int _minpower_, int _selfpower_, lx.gs.map.msg.MatchTeamInfo _team_) {
		this.gid = _gid_;
		this.minpower = _minpower_;
		this.selfpower = _selfpower_;
		this.team = _team_;
	}

	public final boolean _validator_() {
		if (!team._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(gid);
		_os_.marshal(minpower);
		_os_.marshal(selfpower);
		_os_.marshal(team);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		gid = _os_.unmarshal_long();
		minpower = _os_.unmarshal_int();
		selfpower = _os_.unmarshal_int();
		team.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GAddGuardTowerMatch) {
			GAddGuardTowerMatch _o_ = (GAddGuardTowerMatch)_o1_;
			if (gid != _o_.gid) return false;
			if (minpower != _o_.minpower) return false;
			if (selfpower != _o_.selfpower) return false;
			if (!team.equals(_o_.team)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)gid;
		_h_ += minpower;
		_h_ += selfpower;
		_h_ += team.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(gid).append(",");
		_sb_.append(minpower).append(",");
		_sb_.append(selfpower).append(",");
		_sb_.append(team).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


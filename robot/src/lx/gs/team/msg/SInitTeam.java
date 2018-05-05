
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SInitTeam__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SInitTeam extends __SInitTeam__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557806;

	public int getType() {
		return 6557806;
	}

	public lx.gs.team.msg.RoleTeamInfo roleteaminfo; // 角色队伍信息
	public lx.gs.team.msg.Team team; // 队伍信息

	public SInitTeam() {
		roleteaminfo = new lx.gs.team.msg.RoleTeamInfo();
		team = new lx.gs.team.msg.Team();
	}

	public SInitTeam(lx.gs.team.msg.RoleTeamInfo _roleteaminfo_, lx.gs.team.msg.Team _team_) {
		this.roleteaminfo = _roleteaminfo_;
		this.team = _team_;
	}

	public final boolean _validator_() {
		if (!roleteaminfo._validator_()) return false;
		if (!team._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleteaminfo);
		_os_.marshal(team);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleteaminfo.unmarshal(_os_);
		team.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SInitTeam) {
			SInitTeam _o_ = (SInitTeam)_o1_;
			if (!roleteaminfo.equals(_o_.roleteaminfo)) return false;
			if (!team.equals(_o_.team)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += roleteaminfo.hashCode();
		_h_ += team.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleteaminfo).append(",");
		_sb_.append(team).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


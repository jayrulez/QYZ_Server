
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.team.FTeam;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUnFollowLeader__ extends xio.Protocol { }

/** 队友主动解除跟随
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUnFollowLeader extends __CUnFollowLeader__ {
	@Override
	protected void process() {
		new AProcedure<CUnFollowLeader>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(teamid <= 0) {
					return error(ErrorCode.PARAM_ERROR);
				}
				xbean.Team team = FTeam.getTeamByTeamId(teamid);
				if(!FTeam.isTeamMember(teamid, roleid)){
					return error(ErrorCode.NOT_IN_TEAM);
				}
				SUnFollowLeader result = new SUnFollowLeader();
				xbean.TeamMember member = team.getMembers().get(roleid);
				member.setFollow(0);
				result.teamid = teamid;
				response(result);

				//通知对方队长解除跟随操作
				SUnFollowTeamMemberNotify notify = new SUnFollowTeamMemberNotify();
				notify.memberid = roleid;
				tsend(team.getLeaderid(), notify);

				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557850;

	public int getType() {
		return 6557850;
	}

	public long teamid;

	public CUnFollowLeader() {
	}

	public CUnFollowLeader(long _teamid_) {
		this.teamid = _teamid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(teamid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		teamid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUnFollowLeader) {
			CUnFollowLeader _o_ = (CUnFollowLeader)_o1_;
			if (teamid != _o_.teamid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)teamid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(teamid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CUnFollowLeader _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(teamid - _o_.teamid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


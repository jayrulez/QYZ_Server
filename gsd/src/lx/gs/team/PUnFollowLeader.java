package lx.gs.team;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.team.msg.CUnFollowLeader;
import lx.gs.team.msg.SUnFollowLeader;

public class PUnFollowLeader extends AProcedure<CUnFollowLeader> {

	protected PUnFollowLeader(CUnFollowLeader p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		SUnFollowLeader result = new SUnFollowLeader();
		result.teamid = param.teamid;
		if(!FTeam.isTeamMember(param.teamid, roleid)){
			return error(ErrorCode.NOT_IN_TEAM);
		}
		if(FTeam.isTeamLeader(roleid)){
			return error(ErrorCode.PARAM_ERROR);
		}
		
		xbean.TeamMember member = FTeam.getTeamByTeamId(param.teamid).getMembers().get(roleid);
		member.setFollow(0);
		member.setFollowtime(0);
		response(result);
		return true;
	}

}

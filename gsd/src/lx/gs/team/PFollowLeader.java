package lx.gs.team;

import common.ErrorCode;
import gs.AProcedure;
import lx.gs.team.msg.CFollowLeader;
import lx.gs.team.msg.SFollowLeader;
import lx.gs.team.msg.SFollowTeamMemberNotify;

public class PFollowLeader extends AProcedure<CFollowLeader> {

	public PFollowLeader(CFollowLeader param) {
		super(param);
	}

	@Override
	protected boolean doProcess() throws Exception {
		SFollowLeader result = new SFollowLeader();
		if(param.teamid <= 0)
			return error(ErrorCode.PARAM_ERROR);
		
		xbean.Team team = FTeam.getTeamByTeamId(param.teamid);
		if(!FTeam.isTeamMember(param.teamid, roleid)){
			return error(ErrorCode.NOT_IN_TEAM);
		}
		
		//判断是否可以跟随，地图是否在一张地图之类的。。。。
		xbean.TeamMember member = team.getMembers().get(roleid);
		member.setFollow(1);
		member.setFollowtime(System.currentTimeMillis());
		
		result.result = SFollowLeader.RES_OK;
		response(result);

		//通知对方队长跟随操作
		SFollowTeamMemberNotify notify = new SFollowTeamMemberNotify();
		notify.followmemberid = roleid;
		tsend(team.getLeaderid(), notify);

		return true;
	}

}

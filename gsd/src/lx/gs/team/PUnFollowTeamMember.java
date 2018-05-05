package lx.gs.team;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.team.msg.CUnFollowTeamMember;
import lx.gs.team.msg.SUnFollowTeamMember;
import lx.gs.team.msg.SUnFollowTeamMemberNotify;


public class PUnFollowTeamMember extends AProcedure<CUnFollowTeamMember> {

	public PUnFollowTeamMember(CUnFollowTeamMember param) {
		super(param);
	}

	@Override
	protected boolean doProcess() throws Exception {
		SUnFollowTeamMember result = new SUnFollowTeamMember();
		result.unfollowmemberid = param.unfollowmemberid;
		if(param.unfollowmemberid <= 0)
			return error(ErrorCode.PARAM_ERROR);
		
		xbean.Team team = FTeam.getTeamByRoleId(roleid);
		if(!FTeam.isTeamLeader(roleid)){
			return error(ErrorCode.ONLY_LEADER_CAN_UNFOLLOW_TEAMMEMBER);
		}
		
		long toroleid = param.unfollowmemberid;		
		if(!FTeam.isTeamMember(team.getTeamid(), toroleid)){
			return error(ErrorCode.NOT_IN_TEAM);
		}
				
		xbean.TeamMember mem = team.getMembers().get(toroleid);
		mem.setFollow(0);
		mem.setFollowtime(0);
		
		//通知对方队长解除跟随操作
		SUnFollowTeamMemberNotify notify = new SUnFollowTeamMemberNotify();
		notify.memberid = toroleid;
		tsend(toroleid, notify);
		
		result.result = SUnFollowTeamMember.RES_OK;
		response(result);
		return true;
	}

}

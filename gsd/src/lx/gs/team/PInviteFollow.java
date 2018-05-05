package lx.gs.team;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.team.msg.CInviteFollow;
import lx.gs.team.msg.SInviteFollow;
import lx.gs.team.msg.SInviteFollowNotify;

public class PInviteFollow extends AProcedure<CInviteFollow> {

	public PInviteFollow(CInviteFollow param) {
		super(param);
	}

	@Override
	protected boolean doProcess() throws Exception {
		SInviteFollow result = new SInviteFollow();
		if(param.roleid <=0)
			return error(ErrorCode.PARAM_ERROR);
		
		long toroleid = param.roleid;
		if(toroleid <= 0) return error(ErrorCode.ROLE_NOT_FOUND);
		
		xbean.Team team = FTeam.getTeamByRoleId(roleid);
		if(!FTeam.isTeamLeader(roleid)){
			return error(ErrorCode.ONLY_LEADER_CAN_INVITE_FOLLOW);
		}		
		if(!FTeam.isTeamMember(team.getTeamid(), toroleid)){
			return error(ErrorCode.NOT_IN_TEAM);
		}
		long curtime = System.currentTimeMillis();
		Long lasttime = team.getInvitefollow().get(param.roleid);
		if(lasttime != null && curtime - lasttime < TeamModule.INVITE_FOLLOW_TIME_GAP){
			return error(ErrorCode.ALREADY_INVITE);
		}
		team.getInvitefollow().remove(param.roleid);
		team.getInvitefollow().put(toroleid,curtime);
		
		SInviteFollowNotify notify = new SInviteFollowNotify();
		notify.leader = FTeam.convert(team.getMembers().get(roleid));
		tsend(toroleid, notify);
		
		result.result = SInviteFollow.RES_OK;		
		response(result);
		return true;
	}

}

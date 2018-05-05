package lx.gs.team;

import gs.RoleCreateListener;
import lx.gs.team.msg.SInitTeam;
import lx.gs.team.msg.Team;
import xdb.Transaction;

public enum TeamModule implements gs.Module, gs.RoleLoginListener, RoleCreateListener {
	INSTANCE;

	public static int INVITE_JOIN_TEAM_TIME_GAP = 20 * 1000;//20秒
	public static int INVITE_FOLLOW_TIME_GAP = 15 * 1000;
	@Override
	public void start() {
	}

	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		xbean.RoleTeamInfo teamInfo = FTeam.getRoleTeamInfo(roleid);
		if(teamInfo.getTeamid() != 0){
			teamInfo.setTeamid(0);
		}
		Transaction.tsendWhileCommit(roleid, new SInitTeam(FTeam.convert(teamInfo), new Team()));
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {
		//模拟退出队伍
		FTeam.quitTeam(roleid);
	}

	@Override
	public void onRoleCreateInProcedure(long roleid) {
		xbean.RoleTeamInfo teaminfo = xbean.Pod.newRoleTeamInfo();
		teaminfo.setTeamid(0);
		teaminfo.setAutoacceptinvite(1);
		teaminfo.setAutoacceptrequest(1);
		xtable.Role2team.add(roleid, teaminfo);
	}

	@Override
	public void onRoleDeleteInProcedure(long roleid) {
	}
}
package lx.gs.gm;

import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import lx.gs.team.FTeam;

import java.util.HashMap;

@Module(comment="组队模块GM命令")
public class Team {
	@Cmd(comment="获取个人组队信息.")
	public Object getTitle()
	{
		final long roleid = GmSession.current().getRoleid();
		HashMap<String, Object> result = new HashMap<String,Object>();
		xbean.RoleTeamInfo info = FTeam.getRoleTeamInfo(roleid);
		result.put("tinfo", info);
		return result;
	}
	
	@Cmd(comment="获取队伍信息.")
	public Object getTitle(@Param(name="teamid",comment="队伍id")long teamid)
	{
		HashMap<String, Object> result = new HashMap<String,Object>();
		xbean.Team info = FTeam.getTeamByTeamId(teamid);
		if(info != null){
			result.put("team", info);
		}
		return result;
	}
	
	
}
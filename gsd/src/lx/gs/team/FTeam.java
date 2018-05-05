package lx.gs.team;

import cfg.CfgMgr;
import cfg.Const;
import cfg.tips.LocationType;
import gnet.link.Onlines;
import lx.gs.map.FMap;
import lx.gs.role.FRole;
import lx.gs.role.msg.RoleShowInfo4;
import lx.gs.team.msg.*;
import lx.gs.tips.FTips;
import cfg.tips.TipsCode;
import map.msg.XChangeTeam;
import xbean.Pod;
import xdb.Transaction;
import xtable.Role2team;
import xtable.Roleinfos;

import java.util.Map;

public class FTeam {

	/**
	 * 根据角色id获取队伍信息
	 * @param roleid
	 * @return
	 */
	public static xbean.Team getTeamByRoleId(long roleid){
		return getTeamByTeamId(getRoleTeamInfo(roleid).getTeamid());
	}

	public static xbean.Team selectTeamByRoleId(long roleid){
		xbean.RoleTeamInfo roleTeamInfo = selectRoleTeamInfo(roleid);
		return roleTeamInfo == null ? null : xtable.Team.select(roleTeamInfo.getTeamid());
	}

	public static map.msg.Team getProtocolMapTeamByRoleId(long roleId){
		map.msg.Team protocolTeam = convert2Map(selectTeamByRoleId(roleId));
		return protocolTeam == null ? new map.msg.Team() : protocolTeam;
	}

    public static map.msg.TeamInfo getRoleTeamInfo2(long teamid) {
        return xtable.Team.getTable().select(teamid, team -> {
            final map.msg.TeamInfo info = new map.msg.TeamInfo();
            info.teamid = team.getTeamid();
            info.membernum = team.getMembers().size();
            FRole.fillMemberInfo(team.getLeaderid(), info.leader);
            return info;
        });
    }

	public static void syncTeamToMap(long teamId){
		xbean.Team team = selectTeamByTeamId(teamId);
		syncTeamToMap(team);
	}

	public static void syncTeamToMap(xbean.Team team){
		if(team == null)
			return;
		XChangeTeam xChangeTeam = new XChangeTeam(convert2Map(team));
		for (Long memberRoleId : team.getMembers().keySet()) {
			FMap.dispatchMessageInProcedure(memberRoleId, xChangeTeam);
		}
	}

	public static void syncTeam(long roleId){
		xbean.Team team = selectTeamByRoleId(roleId);
		if(team == null){
			return;
		}
		SSyncTeam sSyncTeam = new SSyncTeam(convert(team));
		Transaction.tsendWhileCommit(roleId, sSyncTeam);
	}

	/**
	 * 获取队伍信息
	 * @param teamid
	 * @return
	 */
	public static xbean.Team getTeamByTeamId(long teamid){
		return xtable.Team.get(teamid);
	}

	public static xbean.Team selectTeamByTeamId(long teamid){
		return xtable.Team.select(teamid);
	}
	
	/**
	 * 发送组播的通知给全队人员
	 * @param roleid
	 * @param pro
	 * @return
	 */
	public static boolean broadcastTeamByRoleid(long roleid, xio.Protocol pro){
		xbean.Team team = selectTeamByRoleId(roleid);
		if(team == null) return false;
		xdb.Transaction.tsendWhileCommit(team.getMembers().keySet(), pro);
		return true;
	}
	
	public static boolean broadcastTeamByTeamid(long teamid, xio.Protocol pro){
		xbean.Team team = selectTeamByTeamId(teamid);
		if(team == null) return false;
		xdb.Transaction.tsendWhileCommit(team.getMembers().keySet(), pro);
		return true;
	}
	/**
	 * 获取角色的队伍配置信息，如果为Null创建一个空队伍，判断队伍存在的标准是leaderid=0;
	 * @param roleid
	 * @return
	 */
	public static xbean.RoleTeamInfo getRoleTeamInfo(long roleid){
		xbean.RoleTeamInfo ret = xtable.Role2team.get(roleid);
		if(ret == null){
			TeamModule.INSTANCE.onRoleCreateInProcedure(roleid);
			ret = xtable.Role2team.get(roleid);
		}
		return ret;
	}


	public static xbean.RoleTeamInfo selectRoleTeamInfo(long roleid){
		xbean.RoleTeamInfo ret = xtable.Role2team.select(roleid);
		if(ret == null){
			TeamModule.INSTANCE.onRoleCreateInProcedure(roleid);
			ret = xtable.Role2team.select(roleid);
		}
		return ret;
	}
	/**
	 * 检查memberid是否是teamid中的一员
	 * @param teamid
	 * @param memberid
	 * @return
	 */
	public static boolean isTeamMember(long teamid, long memberid){
		xbean.Team team = selectTeamByTeamId(teamid);
		if(null == team) return false;
		return selectRoleTeamInfo(memberid).getTeamid() == team.getTeamid()
				&& team.getMembers().containsKey(memberid);
	}
	
	/**
	 * 判断roleid是否队长,
	 * @param roleid
	 * @return
	 */
	public static boolean isTeamLeader(long roleid){
		xbean.Team team = selectTeamByRoleId(roleid);
		return team != null
				&& team.getLeaderid() != Const.NULL
				&& team.getLeaderid() == roleid;
	}
	
	public static boolean isTeamFull(xbean.Team team){
		return team != null && team.getMembers().size() >= CfgMgr.team.teammembermaxcount;
	}
	/**
	 * 判断是否已经在队伍中，可能是队长，也可能是
	 * @param roleid
	 * @return
	 */
	public static boolean isInTeam(long roleid){
		xbean.Team team = selectTeamByRoleId(roleid);
		return team != null
				&& team.getLeaderid() != 0
				&& team.getMembers().containsKey(roleid);
	}
	/**
	 * 判断角色是否在线
	 * @param roleid
	 * @return
	 */
	public static boolean isOnLine(long roleid){
		return Onlines.getInstance().find(roleid) != null;
	}

	public static boolean inSameTeam(long roleId1, long roleId2){
		xbean.Team team1 = getTeamByRoleId(roleId1);
		xbean.Team team2 = getTeamByRoleId(roleId2);
		return team1 != null && team2 != null && team1.getTeamid() == team2.getTeamid();
	}

	/**
	 * 创建队伍
	 * @param roleid
	 * @return
	 */
	public static boolean createTeam(long roleid){
		if(isInTeam(roleid)){
			Transaction.tsend(roleid, FTips.create(LocationType.ALERT, TipsCode.ALREADY_IN_TEAM));
			return false;
		}

		long now = System.currentTimeMillis();
		xbean.Team team = xbean.Pod.newTeam();
		team.setLeaderid(roleid);
		team.setCreatetime(now);
		
		xbean.TeamMember member = xbean.Pod.newTeamMember();
		member.setRoleid(roleid);
		member.setJointime(now);
		team.getMembers().put(roleid, member);	
		
		long id = xtable.Team.insert(team);
		team.setTeamid(id);

		getRoleTeamInfo(roleid).setTeamid(id);
		syncTeam(roleid);
		syncTeamToMap(team);
		return true;
	}
	

	public static lx.gs.team.msg.Team convert(xbean.Team team){
		if(null == team) return null;
		lx.gs.team.msg.Team t = new lx.gs.team.msg.Team();
		t.leaderid = team.getLeaderid();
		t.createtime = team.getCreatetime();
		t.teamid = team.getTeamid();
		team.getMembers().forEach((aLong, teamMember) -> t.members.put(aLong, convert(teamMember)));
		return t;
	}

	public static map.msg.Team convert2Map(xbean.Team team){
		if(null == team) return null;
		map.msg.Team t = new map.msg.Team();
		t.teamid = team.getTeamid();
        t.members.addAll(team.getMembers().keySet());
		return t;
	}
	
	public static lx.gs.team.msg.RoleTeamInfo convert(xbean.RoleTeamInfo info){
		return new lx.gs.team.msg.RoleTeamInfo(info.getTeamid(), info.getAutoacceptrequest(),info.getAutoacceptinvite());
	}
	
	public static lx.gs.team.msg.TeamMember convert(xbean.TeamMember member){
		if(null == member) return null;
		lx.gs.team.msg.TeamMember tm = new lx.gs.team.msg.TeamMember();
		tm.roleid = member.getRoleid();
		tm.follow = member.getFollow();
		tm.jointime = member.getJointime();
		FRole.genRoleShowInfo(member.getRoleid(), tm.roleinfo);
		return tm;
	}
	
	public static boolean quitTeam(long roleid){
		xbean.Team team = getTeamByRoleId(roleid);
		if(team == null){
			return false;
		}
		Map<Long, xbean.TeamMember> memberMap = team.getMembers();
		FTips.sendWhileCommit(roleid, LocationType.CENTER, TipsCode.LEAVE_TEAM, Roleinfos.selectName(team.getLeaderid()));
		if(memberMap.size() == 1){
			return breakupTeam(roleid);
		}
		// 通知所有人
		broadcastTeamByTeamid(team.getTeamid(), new SQuitTeam(roleid));

		memberMap.remove(roleid);
		getRoleTeamInfo(roleid).setTeamid(0);

		if(team.getLeaderid() == roleid){
			team.setLeaderid(memberMap.values().stream().min((o1, o2) -> Long.compare(o1.getJointime(), o2.getJointime())).get().getRoleid());
			broadcastTeamByTeamid(team.getTeamid(), new STransferLeader(team.getLeaderid()));
		}

		syncTeamToMap(team);
		FMap.dispatchMessageInProcedure(roleid, new XChangeTeam());
		return true;
	}

	/**
	 * 解散队伍
	 * @param roleid
	 * @return
	 */
	public static boolean breakupTeam(long roleid){
		if(!isTeamLeader(roleid)){
			Transaction.tsend(roleid, FTips.create(LocationType.CENTER, TipsCode.NOT_LEADER));
			return false;
		}

		xbean.Team team = getTeamByRoleId(roleid);
		broadcastTeamByTeamid(team.getTeamid(), new SBreakupTeam());

		XChangeTeam xChangeTeam = new XChangeTeam();

		team.getMembers().forEach((aLong, teamMember) -> {
			getRoleTeamInfo(aLong).setTeamid(0);
			FMap.dispatchMessageInProcedure(aLong, xChangeTeam);
		});
		xtable.Team.remove(team.getTeamid());
		return true;
	}


	public static boolean agreeJoin(long roleid, long requestRoleId){
		if(!isTeamLeader(roleid)){
			Transaction.tsend(roleid, FTips.create(LocationType.ALERT, TipsCode.NOT_LEADER));
			return false;
		}

		if(!FTeam.isOnLine(requestRoleId)){
			Transaction.tsend(roleid, FTips.create(LocationType.CENTER, TipsCode.OFFLINE, Roleinfos.selectName(requestRoleId)));
			return false;
		}

		xbean.Team team = getTeamByRoleId(roleid);

		if(isTeamFull(team)){
			Transaction.tsend(roleid, FTips.create(LocationType.ALERT, TipsCode.TEAM_FULL));
			return false;
		}

		if(isInTeam(requestRoleId)){
			Transaction.tsend(roleid, FTips.create(LocationType.CENTER, TipsCode.ALREADY_IN_TEAM));
			return false;
		}

		return addMember(team, requestRoleId);
	}

	/**
	 * 通知队伍里其他队员member加入了队伍
	 * @param team
	 * @param member
	 */
	static void notifyJoinTeam(xbean.Team team, xbean.TeamMember member){
		SJoinTeamNotify notify = new SJoinTeamNotify(convert(member));
		team.getMembers().keySet().stream()
				.filter(aLong -> aLong != member.getRoleid())
				.forEach(aLong -> Transaction.tsendWhileCommit(aLong, notify));
	}

	public static boolean addMember(xbean.Team team, long roleid) {
		if(isTeamFull(team) || isInTeam(roleid)){
			// 只做检查，不做通知，调用时一般保证满足这两个条件
			return false;
		}

		getRoleTeamInfo(roleid).setTeamid(team.getTeamid());

		xbean.TeamMember member = Pod.newTeamMember();
		member.setJointime(System.currentTimeMillis());
		member.setRoleid(roleid);

		team.getMembers().put(roleid, member);
		team.getRequestforjoin().remove(roleid);
		notifyJoinTeam(team, member);

		syncTeamToMap(team);
		syncTeam(roleid);
		Transaction.tsendWhileCommit(roleid, FTips.create(LocationType.CENTER, TipsCode.JOIN_TEAM, Roleinfos.selectName(team.getLeaderid())));
		return true;
	}

	public static boolean kick(long roleid, long memberid) {

		xbean.Team team = getTeamByRoleId(roleid);
		if(!isTeamLeader(roleid)){
			Transaction.tsend(roleid, FTips.create(LocationType.ALERT, TipsCode.NOT_LEADER));
			return false;
		}
		if(!isTeamMember(team.getTeamid(), memberid)){
			Transaction.tsend(roleid, FTips.create(LocationType.ALERT, TipsCode.NOT_IN_TEAM, Roleinfos.selectName(memberid)));
			return false;
		}
		
		broadcastTeamByTeamid(team.getTeamid(), new SKickoutMember(memberid));

		getRoleTeamInfo(memberid).setTeamid(0);
		team.getMembers().remove(memberid);

		syncTeamToMap(team);
		FMap.dispatchMessageInProcedure(memberid, new XChangeTeam());
		return true;
	}

	public static boolean requestJoin(long roleid, long teamid) {
		xbean.Team team = getTeamByTeamId(teamid);

		if(isInTeam(roleid)){
			Transaction.tsend(roleid, FTips.create(LocationType.CENTER, TipsCode.ALREADY_IN_TEAM));
			return false;
		}

		if(isTeamFull(team)){
			Transaction.tsend(roleid, FTips.create(LocationType.CENTER, TipsCode.TEAM_FULL));
			return false;
		}

		long curtime = System.currentTimeMillis();
		Map<Long, Long> re = team.getRequestforjoin();
		if(re.containsKey(roleid) &&
				(curtime - re.get(roleid))< TeamModule.INVITE_JOIN_TEAM_TIME_GAP){
			Transaction.tsend(roleid, FTips.create(LocationType.CENTER, TipsCode.OPERATION_LATER));
			return false;
		}
		re.put(roleid, curtime);

		if(Role2team.select(team.getLeaderid()).getAutoacceptrequest() > 0) {
			//自动接收申请
			return addMember(team, roleid);
		}
		//给队长发送消息，通知有人申请入队
		Transaction.tsendWhileCommit(team.getLeaderid(), new SRequestJoinTeam(FRole.genRoleShowInfo(roleid, new RoleShowInfo4())));
		return true;
	}
}

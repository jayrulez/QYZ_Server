package lx.gs.map.guardtower;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import cfg.ectype.GuardTowerZone;
import cfg.ectype.TimeControler;
import common.ErrorCode;
import common.RefObject;
import common.Time;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.map.msg.CGuardTowerMatchStart;
import lx.gs.role.FRoleAttr;
import lx.gs.team.FTeam;
import xbean.RoleAttr;
import xbean.Team;
import xbean.TeamMember;
import xtable.Roleinfos;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by live106 on 2016/5/11.
 */
public final class PGuardTowerMatchStart extends AProcedure<CGuardTowerMatchStart> {

    public PGuardTowerMatchStart(CGuardTowerMatchStart cGuardTowerMatchStart) {
        super(cGuardTowerMatchStart);
    }

    @Override
    protected boolean doProcess() throws Exception {
        final xbean.RoleEctype info = FMap.getEctype(roleid);
        if (FMap.isForbidMatch(info)) {
            return error(ErrorCode.FORBID_MATCH);
        }

        TimeControler timeControler = CfgMgr.guardtower.opentime;
        if (Time.getDayMilliseconds(System.currentTimeMillis()) < Time.getTodayMillisecondByHourAndMin(timeControler.hour, timeControler.minute)) {
            return error(ErrorCode.GD_TODAY_NOT_BEGIN);
        }
        if (Time.getDayMilliseconds(System.currentTimeMillis()) > Time.getTodayMillisecondByHourAndMin(timeControler.hour, timeControler.minute) + CfgMgr.guardtower.last * 1000L) {
            return error(ErrorCode.GD_TODAY_END);
        }
        // 这里配置最好能保证区等级段不会有交集
        final int level = Roleinfos.selectLevel(roleid);
        final GuardTowerZone zone = CfgMgr.guardtower.zones.stream().filter(z -> z.levellimit.min <= level && z.levellimit.max >= level).findFirst().orElse(null);
        if (zone == null) {
            return error(ErrorCode.GD_MATCH_NO_ZONE_LEVEL);
        }

        final RefObject<ErrorCode> check = new RefObject<>(ErrorCode.INVALID_ECTYPEID);
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                check.value = FCondition.checkA(roleid, CfgMgr.guardtower.dailylimit, 1, By.Open_Ectype, ConfigId.GUARD_TOWER_ECTYPE, 0);
                // 匹配成功才扣次数
                return false;
            }
        }.call();
        if(check.value.err()) {
            return error(check.value);
        }

        final RoleAttr roleAttr = FRoleAttr.get(roleid);
        if (param.minpower > roleAttr.getTotalcombatpower()) {
            return error(ErrorCode.GD_MATCH_MIN_POWER_TOO_LARGE);
        }
        final List<Long> members = new LinkedList<>();
        if (FTeam.isInTeam(roleid)) {
            // team leader or not
            if (!FTeam.isTeamLeader(roleid)) {
                return error(ErrorCode.ONLY_TEAM_LEADER_CAN_START);
            }
            Team team = FTeam.selectTeamByRoleId(roleid);
            Map<Long, TeamMember> teamMembers = team.getMembers();
            for (long roleid : teamMembers.keySet()) {
                if (!FMap.isInWorldOrFamily(roleid)) {
                    return error(ErrorCode.GD_MATCH_MEMBER_IN_ECTYPE);
                }
                if (roleid == this.roleid) {
                    continue;
                }
                if (FCondition.checkA(roleid, zone.levellimit, 1, By.Guard_Tower_Zone_Check, ConfigId.GUARD_TOWER_ECTYPE, 0).err()) {
                    return error(ErrorCode.GD_MATCH_MEMBER_OUT_of_RANGE);
                }

                new xdb.Procedure() {
                    @Override
                    protected boolean process() {
                        check.value = FCondition.checkA(roleid, CfgMgr.guardtower.dailylimit, 1, By.Open_Ectype, ConfigId.GUARD_TOWER_ECTYPE, 0);
                        if (check.value != ErrorCode.OK) {
                            check.value = ErrorCode.GD_MATCH_MEMBER_NO_CHALLENGE_TIME;
                        }
                        // 匹配成功才扣次数
                        return false;
                    }
                }.call();
                if(check.value.err()) {
                    return error(check.value);
                }

                if (!FMap.isInWorldOrFamily(roleid)) {
                    return error(ErrorCode.GD_MATCH_MEMBER_IN_ECTYPE);
                }
                final xbean.RoleEctype ectypeInfo = FMap.getEctype(roleid);
                if (FMap.isForbidMatch(ectypeInfo)) {
                    return error(teamMembers.size() > 1 ? ErrorCode.SOME_MEMBER_IN_MATCH : ErrorCode.HAS_IN_MATCH);
                }
            }
            teamMembers.keySet().forEach(roleid -> members.add(roleid));
        } else {
            members.add(roleid);
        }

        GuardTowerMatcher matcher = GuardTowerModule.INSTANCE.getMatcher();
        int gid = matcher.calcGroupid(zone.levellimit.max);
        gid = matcher.combatGidWithZonid(gid, zone.zoneid);

        GuardTowerModule.INSTANCE.addMatch(gid, members, param.minpower, roleAttr.getTotalcombatpower());
        return true;
    }
}

package guardtower;

import cfg.CfgMgr;
import cfg.ectype.GuardTower;
import cfg.ectype.GuardTowerZone;
import common.ErrorCode;
import common.Rpc;
import gnet.ServiceServer;
import lx.gs.role.msg.RoleShowInfo4;
import lx.matcher.guardtower.MAddGuardTowerMatch;
import lx.matcher.guardtower.MCancelGuardTowerMatch;
import lx.matcher.guardtower.MGuardTowerMatchSucc;
import map.msg.MCreateMap;
import map.msg.XCreateGuardTowerEctype;
import match.Member;
import match.PVELevelCombatPowerMatcher;
import match.Team;
import serviced.Config;
import xio.Protocol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class GuardTowerMatcher extends PVELevelCombatPowerMatcher {
    public static class GuardTowerMember extends Member{
        private final RoleShowInfo4 roleShowInfo4;

        public GuardTowerMember(long roleid, RoleShowInfo4 roleShowInfo4) {
            super(roleid);
            this.roleShowInfo4 = roleShowInfo4;
        }
    }

    public GuardTowerMatcher(Builder b) {
        super(b);
    }

    public static GuardTowerMatcher create(GuardTower guardTower) {
        final PVELevelCombatPowerMatcher.Builder builder = new PVELevelCombatPowerMatcher.Builder();
        builder.roundInterval = 5000L;
        builder.matchTimeout = CfgMgr.guardtower.matchtimeout * 1000L;
        builder.teamMemberNum = 3;
        builder.levelRangeSplit = new ArrayList<>();
        guardTower.zones.forEach(z -> builder.levelRangeSplit.add(z.levellimit.max));
        return new GuardTowerMatcher(builder);
    }

    @Override
    protected void onMatchStart(Team team) {
        MAddGuardTowerMatch re = new MAddGuardTowerMatch();
        re.err = ErrorCode.OK.getErrorId();
        team.members.forEach(o -> re.members.add(o.roleid));
        ServiceServer.getInstance().sendGsdByRoleid(team.members.get(0).roleid, re);
    }

    @Override
    protected void onMatchError(Team team, ErrorCode err) {
        MAddGuardTowerMatch re = new MAddGuardTowerMatch();
        re.err = err.getErrorId();
        team.members.forEach(o -> re.members.add(o.roleid));
        ServiceServer.getInstance().sendGsdByRoleid(team.members.get(0).roleid, re);
    }

    @Override
    protected void onMatchSucc(List<Team> teams) {
        Set<Long> roles = new HashSet<>();
        final int serverid = ServiceServer.getInstance().getRandomServerid();
        final int zonid = resolveZoneidFromGid(teams.get(0).gid);
        final GuardTowerZone zone = CfgMgr.guardtower.zones_zoneid.get(zonid);

        final XCreateGuardTowerEctype createMsg = new XCreateGuardTowerEctype();
        createMsg.ectypeid = zone.ectypeid;
        createMsg.serverid = Config.getInstance().getServerid();
        createMsg.zoneid = zonid;
        teams.forEach(team -> team.members.forEach(member ->{
            GuardTowerMember guardTowerMember = (GuardTowerMember) member;
            createMsg.roles.put(member.roleid, guardTowerMember.roleShowInfo4.profession);
            roles.add(member.roleid);
        }));

        Rpc.sendRemote(serverid, createMsg, new Rpc.Callback<XCreateGuardTowerEctype>() {
            @Override
            public void onResult(XCreateGuardTowerEctype argument, Protocol result) {
                final MCreateMap res = (MCreateMap)result;
                if(res.retcode == 0) {
                    final MGuardTowerMatchSucc re = new MGuardTowerMatchSucc();
                    re.mapid = res.mapid;
                    teams.forEach(team -> team.members.forEach(member ->{
                        GuardTowerMember guardTowerMember = (GuardTowerMember) member;
                        re.team.members.add(guardTowerMember.roleShowInfo4);
                    }));
                    Set<Integer> gsds = new HashSet<>();
                    roles.forEach(l -> gsds.add(common.Utils.getServeridByRoleid(l)));
                    gsds.forEach(serverid -> ServiceServer.getInstance().sendGsd(serverid, re));
                } else {
                    onTimeout(createMsg);
                }
            }

            @Override
            public void onTimeout(XCreateGuardTowerEctype argument) {
                for(Team team : teams) {
                    onMatchError(team, ErrorCode.INTERNAL_ERR);
                }
            }
        });
    }

    @Override
    protected void onUnmatchError(long roleid, ErrorCode err) {
        final MAddGuardTowerMatch re = new MAddGuardTowerMatch();
        re.err = err.getErrorId();
        re.members.add(roleid);
        ServiceServer.getInstance().sendGsdByRoleid(roleid, re);
    }

    @Override
    protected void onTeamUnmatchSucc(Team team) {
        final MCancelGuardTowerMatch re = new MCancelGuardTowerMatch();
        re.err = ErrorCode.OK.getErrorId();
        re.cancelmembers.addAll(team.members.stream().map(member -> member.roleid).collect(Collectors.toList()));
        re.teammembers.addAll(team.members.stream().map(o -> o.roleid).collect(Collectors.toList()));
        ServiceServer.getInstance().sendGsdByRoleid(team.members.get(0).roleid, re);
    }

    private int resolveZoneidFromGid(long gid) {
        return (int) (gid & 0x00FF);
    }

}

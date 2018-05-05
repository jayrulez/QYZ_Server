package teamfight;

import cfg.CfgMgr;
import common.ErrorCode;
import common.Rpc;
import gnet.ServiceServer;
import lx.gs.role.msg.RoleShowInfo4;
import lx.matcher.MAddTeamFightMatch;
import lx.matcher.MCancelTeamFightMatch;
import lx.matcher.MTeamFightMatchSucc;
import map.msg.MCreateMap;
import map.msg.TeamFightMember;
import map.msg.XCreateTeamFight;
import match.Member;
import match.PVEMatcher;
import match.ReadyTeam;
import match.Team;
import serviced.Config;
import xdb.Trace;
import xio.Protocol;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/5/11.
 */
public class TeamFightMatcher extends match.PVPMatcher {

    public final static class EMember extends Member {
        public final lx.gs.role.msg.RoleShowInfo4 info;
        public EMember(lx.gs.role.msg.RoleShowInfo4 info) {
            super(info.roleid);
            this.info = info;
        }
    }
    
    public final static class ETeam extends Team {
        public final int viplevel;
        private final List<Integer> roundViplevelDeltas;
        private int round;

        public ETeam(long gid, List<Member> members, int viplevel, List<Integer> roundViplevelDeltas) {
            super(gid, members);
            this.viplevel = viplevel;
            this.roundViplevelDeltas = roundViplevelDeltas;
            this.round = 0;
        }

        @Override
        public boolean isMatch(Team other) {
            final ETeam e = (ETeam)other;
            final boolean result =  round >= roundViplevelDeltas.size() || Math.abs(viplevel - e.viplevel) <= roundViplevelDeltas.get(round);
            Trace.info("Team a:{} b:{} ismatch:{}", this, other, result);
            return result;
        }

        @Override
        public boolean matchConditionChangeOnNewRound() {
            return ++round <= roundViplevelDeltas.size();
        }

        @Override
        public String toString() {
            return String.format("%s{gid:%s members:%s viplevel:%s round:%s}",
                    this.getClass().getName(), gid, members, viplevel, round);
        }
    }

    public final static class EReadyTeam extends ReadyTeam {
        private final List<Integer> roundViplevelDeltas;
        private int round;
        public EReadyTeam(List<Team> teams, ETeam combineTeam, List<Integer> roundViplevelDeltas) {
            super(teams, combineTeam);
            this.roundViplevelDeltas = roundViplevelDeltas;

            this.round = 0;
        }

        @Override
        public boolean isMatch(ReadyTeam other) {
            final ETeam e = (ETeam)(((EReadyTeam)other).combineTeam);
            return round >= roundViplevelDeltas.size() || Math.abs(((ETeam)combineTeam).viplevel - e.viplevel) <= roundViplevelDeltas.get(round);
        }

        @Override
        public boolean matchConditionChangeOnNewRound() {
            return ++round < roundViplevelDeltas.size();
        }
    }

    private final List<Integer> roundViplevelDeltas;
    public TeamFightMatcher(Builder b, List<Integer> roundViplevelDeltas) {
        super(b);
        this.roundViplevelDeltas = roundViplevelDeltas;
    }

    public ETeam createTeam(long gid, int vipLevel, List<RoleShowInfo4> members) {
        return new ETeam(gid, members.stream().map(m -> new EMember(m)).collect(Collectors.toList()), vipLevel, this.roundViplevelDeltas);
    }

    @Override
    protected ReadyTeam createReadyGroup(List<Team> teams) {
        final long gid = teams.get(0).gid;
        final int viplevel = teams.stream().mapToInt(t -> ((ETeam)t).viplevel).min().getAsInt();
        final List<Member> members = teams.stream().flatMap(t -> t.members.stream()).collect(Collectors.toList());
        final ETeam combineTeam = new ETeam(gid, members, viplevel, roundViplevelDeltas);
        return new EReadyTeam(teams, combineTeam, roundViplevelDeltas);
    }

    public static TeamFightMatcher create(cfg.ectype.TeamFight teamfight) {
        final Builder b = new Builder();
        PVEMatcher.Builder eb = new PVEMatcher.Builder();
        eb.matchTimeout = teamfight.timeouttime * 1000L;
        eb.roundInterval = teamfight.roundtime * 1000L;
        eb.teamMemberNum = teamfight.matchnum;
        b.builder = eb;
        return new TeamFightMatcher(b, teamfight.roundvipdeltagroup);
    }

    @Override
    protected void onMatchStart(Team team) {
        onMatchError(team, ErrorCode.OK);
    }

    @Override
    protected void onMatchError(Team team, ErrorCode err) {
        final MAddTeamFightMatch re = new MAddTeamFightMatch();
        re.err = err.getErrorId();
        for(Member m : team.members)
            re.members.add(m.roleid);
        ServiceServer.getInstance().sendGsdByRoleid(re.members.get(0), re);
    }

    @Override
    protected void onMatchSucc(ReadyTeam team1, ReadyTeam team2) {
        Trace.info("TeamFightMatcher match succ. team1:{} team2:{}", team1, team2);

        final int serverid = ServiceServer.getInstance().getRandomServerid();

        final XCreateTeamFight msg = new XCreateTeamFight();
        msg.ectypeid = CfgMgr.teamfight.id;
        msg.serverid = Config.getInstance().getServerid();
        msg.levelgroupid = (int)team1.combineTeam.gid;
        for(Team team : team1.teams) {
            for (Member member : team.members) {
                final EMember m = (EMember) member;
                msg.team1.add(new TeamFightMember(m.roleid, m.info.profession));
            }
        }
        for(Team team : team2.teams) {
            for (Member member : team.members) {
                final EMember m = (EMember) member;
                msg.team2.add(new TeamFightMember(m.roleid, m.info.profession));
            }
        }
        final List<Team> teams = new ArrayList<>(team1.teams);
        teams.addAll(team2.teams);

        Rpc.sendRemote(serverid, msg, new Rpc.Callback<XCreateTeamFight>() {
            @Override
            public void onResult(XCreateTeamFight argument, Protocol result) {
                final MCreateMap res = (MCreateMap)result;
                if(res.retcode == 0) {
                    final MTeamFightMatchSucc re = new MTeamFightMatchSucc();
                    re.mapid = res.mapid;
                    for(Team team : team1.teams) {
                        for (Member member : team.members) {
                            re.team1.members.add(((EMember) member).info);
                        }
                    }
                    for(Team team : team2.teams) {
                        for (Member member : team.members) {
                            re.team2.members.add(((EMember) member).info);
                        }
                    }
                    for(Team team : teams) {
                        re.myteammembers.clear();;
                        for (Member member : team.members) {
                            re.myteammembers.add(member.roleid);
                        }
                        ServiceServer.getInstance().sendGsdByRoleid(team.members.get(0).roleid, re);
                    }
                } else {
                    onTimeout(msg);
                }
            }

            @Override
            public void onTimeout(XCreateTeamFight argument) {
                for(Team team : teams) {
                    onMatchError(team, ErrorCode.INTERNAL_ERR);
                }
            }
        });
    }

    @Override
    protected void onUnmatchError(long roleid, ErrorCode err) {
        Trace.info("TeamFightMatcher unmatch error. roleid:{} err:{}", roleid, err);
        final MCancelTeamFightMatch re = new MCancelTeamFightMatch();
        re.err = err.getErrorId();
        re.members.add(roleid);
        ServiceServer.getInstance().sendGsdByRoleid(roleid, re);
    }

    @Override
    protected void onTeamUnmatchSucc(Team team) {
        final MCancelTeamFightMatch re = new MCancelTeamFightMatch();
        for(Member member : team.members)
            re.members.add(member.roleid);
        ServiceServer.getInstance().sendGsdByRoleid(team.members.get(0).roleid, re);
    }
}

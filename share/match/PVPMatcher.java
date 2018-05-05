package match;

import common.ErrorCode;
import xdb.Trace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/5/9.
 *
 * 同时匹配队友和对手
 */
public abstract class PVPMatcher implements IMatcher{
    public static class Builder {
        public PVEMatcher.Builder builder;
    }

    private static class Info {
        final Team team;
        final ReadyTeam readyGroup;
        Info(Team team, ReadyTeam readyGroup) {
            this.team = team;
            this.readyGroup = readyGroup;
        }
    }

    private final PVEMatcher teamMateMatcher;
    private final long roundInterval;
    private final long matchTimeout;

    private final HashMap<Long, List<ReadyTeam>> readyGroups = new HashMap<>();
    private final HashMap<Long, Info> roleid2ReadyTeam = new HashMap<>();

    private long lastUpdateTime;
    public PVPMatcher(Builder b) {
        this.roundInterval = b.builder.roundInterval;
        this.matchTimeout = b.builder.matchTimeout;
        this.teamMateMatcher = new PVEMatcher(b.builder) {
            @Override
            protected void onMatchStart(Team team) {
                PVPMatcher.this.onMatchStart(team);
            }

            @Override
            protected void onMatchError(Team team, ErrorCode err) {
                PVPMatcher.this.onMatchError(team, err);
            }

            @Override
            protected void onTeamUnmatchSucc(Team team) {
                PVPMatcher.this.onTeamUnmatchSucc(team);
            }

            @Override
            protected void onUnmatchError(long roleid, ErrorCode err) {
                PVPMatcher.this.onUnmatchError(roleid, err);
            }

            @Override
            protected void onMatchSucc(List<Team> teams) {
                onReady(teams);
            }
        };
    }


    protected abstract ReadyTeam createReadyGroup(List<Team> teams);
    protected abstract void onMatchStart(Team team);
    protected abstract void onMatchError(Team team, ErrorCode err);
    protected abstract void onMatchSucc(ReadyTeam team1, ReadyTeam team2);
    protected abstract void onUnmatchError(long roleid, ErrorCode err);
    protected abstract void onTeamUnmatchSucc(Team team);

    protected final boolean isMatch(ReadyTeam a, ReadyTeam b) {
        return a != b && a.isMatch(b) && b.isMatch(a);
    }

    @Override
    public final void add(Team team) {
        if(team.members.stream().anyMatch(m -> roleid2ReadyTeam.containsKey(m.roleid))){
            onMatchError(team, team.members.size() > 1 ? ErrorCode.SOME_MEMBER_IN_MATCH : ErrorCode.HAS_IN_MATCH);
            return;
        }

        this.teamMateMatcher.add(team);
//        if(roleid2ReadyTeam.containsKey(team.members.get(0).roleid)) {
//            onMatchStart(team);
//        }
    }

    @Override
    public final void removeTeamByRoleid(long roleid) {
        final Info info = roleid2ReadyTeam.get(roleid);
        if(info == null) {
            // removeTeamByRoleid from not ready
            this.teamMateMatcher.removeTeamByRoleid(roleid);
        } else {
            // removeTeamByRoleid from ready
            final ReadyTeam rg = info.readyGroup;
            this.readyGroups.get(info.team.gid).remove(rg);
            onTeamUnmatchSucc(info.team);
            info.team.members.forEach(m -> roleid2ReadyTeam.remove(m.roleid));
            rg.teams.stream().filter(t -> t != info.team).forEach(this.teamMateMatcher::add);
//            if(info.team.members.size() > 1) {
//                //info.team.members.forEach(m -> roleid2ReadyTeam.remove(m.roleid));
//                //rg.teams.remove(info.team);
//                //info.team.members.removeIf(m -> m.roleid == roleid);
//                rg.teams.stream().forEach(this.teamMateMatcher::add);
//            } else {
//            }
        }
    }

    @Override
    public final void removeAllTeam() {
        this.teamMateMatcher.removeAllTeam();
        new HashSet<>(roleid2ReadyTeam.values()).forEach(this::removeTeam);
    }

    private void removeTeam(Info info) {
        final ReadyTeam rg = info.readyGroup;
        this.readyGroups.get(info.team.gid).remove(rg);
        info.team.members.forEach(m -> roleid2ReadyTeam.remove(m.roleid));
        rg.teams.stream().filter(t -> t != info.team).forEach(this::add);
        onTeamUnmatchSucc(info.team);
    }

    private List<ReadyTeam> getReadyGroups(long gid) {
        List<ReadyTeam> groups = readyGroups.get(gid);
        if(groups == null) {
            groups = new ArrayList<>();
            readyGroups.put(gid, groups);
        }
        return groups;
    }

    private ReadyTeam findMatch(ReadyTeam rg, List<ReadyTeam> gs) {
        assert(rg.available);
        for(ReadyTeam g : gs) {
            assert(g.available);
            if(isMatch(rg, g))
                return g;
        }
        return null;
    }

    protected final void onReady(List<Team> teams) {
        Trace.info("PVPMatch.onReady teams:{}", teams);
        final ReadyTeam ng = createReadyGroup(teams);
        final List<ReadyTeam> groups = getReadyGroups(ng.combineTeam.gid);
        final ReadyTeam mg = findMatch(ng, groups);
        if(mg != null) {
            mg.available = false;
            groups.remove(mg);
            mg.teams.stream().flatMap(t -> t.members.stream()).forEach(m -> roleid2ReadyTeam.remove(m.roleid));
            onMatchSucc(ng, mg);
        } else {
            groups.add(ng);
            teams.forEach(t -> t.members.forEach(m -> roleid2ReadyTeam.put(m.roleid, new Info(t, ng))));
        }
    }

    @Override
    public void update(long now) {
        if(lastUpdateTime > now - roundInterval) return;
        //Trace.info("PVPMatch.update");
        lastUpdateTime = now;
        teamMateMatcher.update(now);

        readyGroups.forEach((gid, groups) -> {
            for(ReadyTeam team : groups.stream().filter(g -> g.matchConditionChangeOnNewRound()).collect(Collectors.toList())) {
                if(!team.available) continue;
                final ReadyTeam another = findMatch(team, groups);
                if(another != null) {
                    team.available = false;
                    another.available = false;
                    groups.remove(team);
                    groups.remove(another);
                    team.combineTeam.members.forEach(m -> roleid2ReadyTeam.remove(m.roleid));
                    another.combineTeam.members.forEach(m -> roleid2ReadyTeam.remove(m.roleid));
                    onMatchSucc(team, another);
                }
            }

            for(ReadyTeam timeoutTeam : groups.stream().filter(ts -> ts.teams.stream()
                    .anyMatch(t -> t.matchBeginTime < now - matchTimeout)).collect(Collectors.toList())) {
                groups.remove(timeoutTeam);
                for (Team team : timeoutTeam.teams) {
                    if (team.matchBeginTime < now - matchTimeout) {
                        team.members.forEach(m -> roleid2ReadyTeam.remove(m.roleid));
                        onMatchError(team, ErrorCode.MATCH_TIMEOUT);
                    } else {
                        team.members.forEach(m -> roleid2ReadyTeam.remove(m.roleid));
                        teamMateMatcher.add(team);
                    }
                }
            }
        });
    }
}

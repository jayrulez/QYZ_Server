package match;

import common.ErrorCode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/5/9.
 *
 * 只匹配队友
 */
public abstract class PVEMatcher implements IMatcher{
    public static class Builder {
        public int teamMemberNum;
        public long roundInterval;
        public long matchTimeout;
    }

    private final int teamMemberNum;
    private final long roundInterval;
    private final long matchTimeout;
    private final HashMap<Long, Group> groups = new HashMap<>();
    private final HashMap<Long, Team> roleid2Team = new HashMap<>();

    private long lastUpdateTime;

    public PVEMatcher(Builder b) {
        this.teamMemberNum = b.teamMemberNum;
        this.roundInterval = b.roundInterval;
        this.matchTimeout = b.matchTimeout;
        this.lastUpdateTime = 0;
    }


    protected abstract void onMatchStart(Team team);
    protected abstract void onMatchError(Team team, ErrorCode err);
    protected abstract void onMatchSucc(List<Team> teams);
    protected abstract void onUnmatchError(long roleid, ErrorCode err);
    protected abstract void onTeamUnmatchSucc(Team team);

    @Override
    public final void add(Team team) {
        if(team.members.isEmpty()) {
            onMatchError(team, ErrorCode.TEAM_IS_EMPTY);
            return;
        }
        if(team.members.stream().anyMatch(m -> roleid2Team.containsKey(m.roleid))) {
            onMatchError(team, team.members.size() > 1 ? ErrorCode.SOME_MEMBER_IN_MATCH : ErrorCode.HAS_IN_MATCH);
            return;
        }
        onMatchStart(team);
        if(team.members.size() >= teamMemberNum) {
            onMatchSucc(Arrays.asList(team));
            return;
        }

        final Group g = getGroup(team.gid);
        final List<Team> matches = findMatches(team, g);
        if(matches != null) {
            matches.forEach(Team::removeGroup);
            matches.forEach(t -> t.members.forEach(m -> roleid2Team.remove(m.roleid)));
            matches.add(team);
            onMatchSucc(matches);
        } else {
            team.addGroup(g);
            team.members.forEach(m -> roleid2Team.put(m.roleid, team));
        }
    }

    @Override
    public final void removeTeamByRoleid(long roleid) {
        final Team team = roleid2Team.get(roleid);
        if(team == null) {
            onUnmatchError(roleid, ErrorCode.NOT_INT_MATCH);
            return;
        }

        removeTeam(team);
    }

    @Override
    public final void removeAllTeam() {
        new HashSet<>(roleid2Team.values()).forEach(this::removeTeam);
    }

    private void removeTeam(Team team) {
        team.members.forEach(m -> roleid2Team.remove(m.roleid));
        team.removeGroup();
        onTeamUnmatchSucc(team);
    }

    private final Group getGroup(long gid) {
        Group g = groups.get(gid);
        if(g == null) {
            g = new Group(teamMemberNum);
            groups.put(gid, g);
        }
        return g;
    }

    private final boolean isMatch(Team a, Team b) {
        return a != b && a.isMatch(b) && b.isMatch(a);
    }

    private final List<Team> findMatches(Team team, Group g) {
        final List<Team> matches = new ArrayList<>();
        int needMemberNum = teamMemberNum - team.members.size();
        for(int index = needMemberNum ; index > 0 ; index--) {
            for(Team a : g.teamByMemberNums.get(index)) {
                assert(a.avaliable);
                if(isMatch(team, a)) {
                    matches.add(a);
                    needMemberNum -= index;
                    if(needMemberNum <= 0)
                        return matches;
                    if(needMemberNum < index) {
                        index = needMemberNum;
                        break;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void update(long now) {
        if(this.lastUpdateTime + roundInterval > now) return;
        this.lastUpdateTime = now;
        //Trace.info("PVEMatch.update");
        for(Group g : groups.values()) {
            for(Team team : g.teamByMemberNums.stream().flatMap(teams -> teams.stream()
                    .filter(t -> t.matchConditionChangeOnNewRound())).collect(Collectors.toList())) {
                // 有可能在寻找匹配的过程中.一些activeteam被之前的team匹配了
                if(team.avaliable) {
                    final List<Team> matches = findMatches(team, g);
                    if(matches != null) {
                        // 注意 它的matches.add 与 matches.removeGroup的顺序.与 this.add 中的
                        // matches.add 和 matches.removeGroup相反.
                        matches.add(team);
                        matches.forEach(t -> {
                            t.removeGroup();
                            t.members.forEach(m -> roleid2Team.remove(m.roleid));
                        });
                        onMatchSucc(matches);
                    }
                }
            }

            for (Team team : g.teamByMemberNums.stream().flatMap(teams -> teams.stream()
                    .filter(t -> t.matchBeginTime < now - matchTimeout)).collect(Collectors.toList())) {
                team.removeGroup();
                team.members.forEach(m -> roleid2Team.remove(m.roleid));
                onMatchError(team, ErrorCode.MATCH_TIMEOUT);
            }
        }
    }
}

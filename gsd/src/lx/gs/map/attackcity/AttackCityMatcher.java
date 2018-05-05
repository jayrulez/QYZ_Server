package lx.gs.map.attackcity;

import cfg.ectype.AttackCityLevelSection;
import common.ErrorCode;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by HuangQiang on 2016/5/27.
 */
public class AttackCityMatcher {
    final public static class Team {
        public final int gid;
        public final List<Long> members;
        public Team(int gid, List<Long> members) {
            this.gid = gid;
            this.members = members;
        }
    }
    final static class Group {
        final Set<Team> teams = new HashSet<>();
    }

    private final Map<Integer, Group> groups = new HashMap<>();
    private final Map<Long, Team> roleid2Teams = new HashMap<>();

    private final List<AttackCityLevelSection> sections;
    public AttackCityMatcher(cfg.ectype.AttackCity attackCityCfg) {
        this.sections = attackCityCfg.sections;
    }

    public final int calcGroupid(int level) {
        for(int i = 0 ; i < this.sections.size() ; i++) {
            final cfg.cmd.condition.MaxLevel requireLevel = this.sections.get(i).requirelevel;
            if(level <= requireLevel.level)
                return i;
        }
        return this.sections.size() - 1;
    }

    public Group getGroup(int gid) {
        Group g = groups.get(gid);
        if(g == null) {
            g = new Group();
            groups.put(gid, g);
        }
        return g;
    }

    public boolean inEnroll(long roleid) {
        return roleid2Teams.containsKey(roleid);
    }

    public final ErrorCode addTeam(Team team) {
        if(team.members.stream().anyMatch(roleid2Teams::containsKey))
            return team.members.size() > 1 ? ErrorCode.SOME_MEMBER_IN_MATCH : ErrorCode.HAS_IN_MATCH;
        getGroup(team.gid).teams.add(team);
        for(Long roleid : team.members) {
            roleid2Teams.put(roleid, team);
        }
        return ErrorCode.OK;
    }

    public void doCatelog(Consumer<Team> cb) {
        groups.forEach((gid, group) -> {
            final int teamMemberNum = this.sections.get(gid).groupnum;
            Team resultTeam = new Team(gid, new ArrayList<>());
            for(Team team : group.teams) {
                 resultTeam.members.addAll(team.members);
                if(resultTeam.members.size() >= teamMemberNum) {
                    cb.accept(resultTeam);
                    resultTeam.members.clear();
                }
            }
            if(!resultTeam.members.isEmpty()) {
                cb.accept(resultTeam);
            }
        });

        reset();
    }

    public void reset() {
        groups.clear();
        roleid2Teams.clear();
    }

}

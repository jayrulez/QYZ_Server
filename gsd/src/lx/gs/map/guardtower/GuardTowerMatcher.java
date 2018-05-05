package lx.gs.map.guardtower;

import common.ErrorCode;
import match.PVELevelCombatPowerMatcher;
import match.Team;

import java.util.List;

/**
 * Created by live106 on 2016/5/12.
 */
public final class GuardTowerMatcher extends PVELevelCombatPowerMatcher {

    public GuardTowerMatcher(Builder b) {
        super(b);
    }

    public int combatGidWithZonid(int gid, int zoneid) {
        return (gid << 8) | zoneid;
    }

    @Override
    protected void onMatchStart(Team team) {
    }

    @Override
    protected void onMatchError(Team team, ErrorCode err) {
    }

    @Override
    protected void onMatchSucc(List<Team> teams) {
    }

    @Override
    protected void onUnmatchError(long roleid, ErrorCode err) {
    }

    @Override
    protected void onTeamUnmatchSucc(Team team) {
    }
}

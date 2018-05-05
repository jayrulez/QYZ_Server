package match;

import xdb.Trace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangQiang on 2016/5/10.
 */
public abstract class PVELevelCombatPowerMatcher extends match.PVEMatcher {

    public final static class ETeam extends Team {
        public final int combatPower;
        public final int requireTeamMateMinCombatPower;

        public ETeam(long gid, List<Member> members, int combatPower, int requireTeamMateMinCombatPower) {
            super(gid, members);
            this.combatPower = combatPower;
            this.requireTeamMateMinCombatPower = requireTeamMateMinCombatPower;
        }

        @Override
        public boolean isMatch(Team other) {
            final ETeam e = (ETeam)other;
            final boolean result =  e.combatPower >= requireTeamMateMinCombatPower;
            Trace.info("Team a:{} b {} ismatch:{}", this, other, result);
            return result;
        }

        @Override
        public boolean matchConditionChangeOnNewRound() {
            return false;
        }

        @Override
        public String toString() {
            return String.format("%s{gid:%s members:%s combatpower:%s requirecombatpoer:%s}",
                    this.getClass().getName(), gid, members, combatPower, requireTeamMateMinCombatPower);
        }
    }

    public final static class Builder extends match.PVEMatcher.Builder {
        public List<Integer> levelRangeSplit = new ArrayList<>();
    }


    private final List<Integer> levelRangeSplit;
    public PVELevelCombatPowerMatcher(Builder b) {
        super(b);

        this.levelRangeSplit = new ArrayList<>(b.levelRangeSplit);
        this.levelRangeSplit.add(Integer.MAX_VALUE);
    }

    public final int calcGroupid(int level) {
        for(int i = 0 ; i < this.levelRangeSplit.size() ; i++) {
            if(this.levelRangeSplit.get(i) >= level)
                return i;
        }
        return this.levelRangeSplit.size() - 1;
    }

    // ETeam createTeam(xx, yy, zz) TODO
}

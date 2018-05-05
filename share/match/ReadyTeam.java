package match;

import java.util.List;

/**
 * Created by HuangQiang on 2016/5/9.
 */
public class ReadyTeam {
    public final List<? extends Team> teams;
    public Team combineTeam;
    public boolean available;

    public ReadyTeam(List<? extends Team> teams, Team combineTeam) {
        this.teams = teams;
        this.combineTeam = combineTeam;
        this.available = true;
    }

    public boolean isMatch(ReadyTeam other) {
        return true;
    }

    public boolean matchConditionChangeOnNewRound() {
        return false;
    }
}

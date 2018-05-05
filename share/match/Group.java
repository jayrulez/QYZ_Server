package match;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangQiang on 2016/5/9.
 */
public class Group {
    public Group(int memberNum) {
        for(int i = 0 ; i <= memberNum ; i++)
            teamByMemberNums.add(new ArrayList<>());

    }

    public final List<List<Team>> teamByMemberNums = new ArrayList<>();


    public final void addTeam(Team team) {
        teamByMemberNums.get(team.members.size()).add(team);
    }

    public final void removeTeam(Team team) {
        teamByMemberNums.get(team.members.size()).remove(team);
    }
}

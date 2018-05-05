package multistory;

import match.Member;
import match.Team;
import teamfight.TeamFightMatcher;
import lx.gs.map.msg.MatchMultiStroyInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiong on 2016/5/10.
 */
public class MultiSotryTeam extends Team {

    public static class MultiMember extends Member {
        public final lx.gs.map.msg.MatchMultiStroyInfo info;

        public MultiMember(lx.gs.map.msg.MatchMultiStroyInfo info) {
            super(info.roleid);
            this.info = info;
        }
    }

    private int maxNum;
    public static List<MultiMember> creatMembers(List<MatchMultiStroyInfo> roleInfos){
        List<MultiMember> members = new ArrayList<>();
        roleInfos.forEach(e -> members.add(new MultiMember(e)));
        return members;
    }

    public MultiSotryTeam(List<MultiMember> members, int ectypeid, int maxNum){
        super(ectypeid, members);
        this.maxNum = maxNum;
    }

    @Override
    public boolean isMatch(Team other) {
        return true;
    }

    @Override
    public boolean matchConditionChangeOnNewRound() {
        return false;
    }
}

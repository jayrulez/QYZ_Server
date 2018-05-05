package match;

import java.util.List;

/**
 * Created by HuangQiang on 2016/5/9.
 */
public class Team {
    public final List<? extends Member> members;
    public final long gid;
    public final long matchBeginTime;

    public boolean avaliable;
    public Group group;

    public Team(long gid, List<? extends Member> members) {
        this.members = members;
        this.gid = gid;
        this.matchBeginTime = System.currentTimeMillis();
        this.avaliable = false;
    }

    @Override
    public String toString() {
        return String.format("Team{gid:%s members:%s}", gid, members);
    }

    public final void addGroup(Group g) {
        assert(this.group == null);
        g.addTeam(this);
        this.group = g;
        this.avaliable = true;
    }

    public final void removeGroup() {
        assert(this.group != null);
        this.group.removeTeam(this);
        this.group = null;
        this.avaliable = false;
    }

    public boolean isMatch(Team other) {
        return true;
    }

    // 通知下一轮匹配开始.如果匹配条件有变化,比如说放松了要求 return true
    // 如果匹配条件无变化,return false
    public boolean matchConditionChangeOnNewRound() {
        return false;
    }
}

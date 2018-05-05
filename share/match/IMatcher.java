package match;

/**
 * Created by HuangQiang on 2016/5/10.
 */
public interface IMatcher {
    void add(Team team);
    void removeTeamByRoleid(long roleid);
    void removeAllTeam();
    //void removeTeamByRoleid(long roleid);
    void update(long now);
}

package teamfight;

import cfg.CfgMgr;

/**
 * Created by HuangQiang on 2016/8/18.
 */
public enum TeamFightModule {
    INSTANCE;

    public final static match.Manager<TeamFightMatcher> matcher = new match.Manager<>(TeamFightMatcher.create(CfgMgr.teamfight));

    public void start() {
        matcher.start();
    }
}

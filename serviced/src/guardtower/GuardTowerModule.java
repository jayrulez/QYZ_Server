package guardtower;

import cfg.CfgMgr;

/**
 * Created by live106 on 2016/5/12.
 */
public enum GuardTowerModule {
    INSTANCE;

    public final static match.Manager<GuardTowerMatcher> matcher = new match.Manager<>(GuardTowerMatcher.create(CfgMgr.guardtower));

    public void start() {
        matcher.start();
    }

}

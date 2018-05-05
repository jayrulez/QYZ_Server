package multistory;

import cfg.ectype.TeamStoryEctype;
import match.Manager;

/**
 * Created by xiong on 2016/8/19.
 */
public enum MutilStoryModule {
    INSTANCE;

    public final static int MULTI_STORY_MATCH_NUMS = 3;
    public final static Manager<MultiSotryPVEMatcher> multiStoryManager = new Manager<>(MultiSotryPVEMatcher.creat(MULTI_STORY_MATCH_NUMS, 5 * 1000, TeamStoryEctype.MATCH_TIME_MAX * 1000));
    public void start() {
        multiStoryManager.start();
    }
}

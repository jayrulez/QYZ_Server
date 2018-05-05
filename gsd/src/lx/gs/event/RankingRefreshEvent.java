package lx.gs.event;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jin Shuai
 */
public class RankingRefreshEvent extends AbstractEvent {
    public final Map<Integer, Integer> rankingMap;

    public RankingRefreshEvent(long roleId, Map<Integer, Integer> rankingMap) {
        super(roleId, EventType.RANKING_REFRESH);
        this.rankingMap = new HashMap<>();
        this.rankingMap.putAll(rankingMap);
    }
}

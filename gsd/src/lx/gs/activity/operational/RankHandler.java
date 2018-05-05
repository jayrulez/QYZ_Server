package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.Rank;
import common.ErrorCode;
import lx.gs.event.AbstractEvent;
import lx.gs.event.RankingRefreshEvent;

/**
 * @author Jin Shuai
 */
public class RankHandler extends OperationalActivityHandler<Rank> {

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        Rank rank = cast(entry);
        RankingRefreshEvent rankingRefreshEvent = event.cast();
        return rankingRefreshEvent.rankingMap.getOrDefault(rank.type, Integer.MAX_VALUE) <= rank.ranking;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry){
        return ErrorCode.OK;
    }

    @Override
    protected boolean onlyTriggerByEvent() {
        return true;
    }

}

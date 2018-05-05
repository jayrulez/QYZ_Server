package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.ArenaRanking;
import common.ErrorCode;
import lx.gs.arena.FArena;
import lx.gs.event.AbstractEvent;

/**
 * @author Jin Shuai
 */
public class ArenaRankingHandler extends OperationalActivityHandler<ArenaRanking> {
    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        int ranking = FArena.get(roleId).getBestrank();
        return ranking > 0 && ranking <= cast(entry).num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }

}

package lx.gs.activity.operational;

import cfg.currency.CurrencyType;
import cfg.operationalactivity.AchievementPoints;
import cfg.operationalactivity.ActivityEntry;
import common.ErrorCode;
import lx.gs.event.AbstractEvent;

/**
 * Created by xiong on 2016/6/27.
 */
public class AchievementHandler extends OperationalActivityHandler<AchievementPoints> {

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        long count = xtable.Roleinfos.selectCurrencys(roleId).getOrDefault(CurrencyType.ChengJiu, 0L);
        return count >= cast(entry).num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }
}

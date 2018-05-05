package lx.gs.activity.operational;

import cfg.cmd.ConfigId;
import cfg.cmd.condition.Condition;
import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.DailyGift;
import common.ErrorCode;
import lx.gs.cmd.FCondition;
import lx.gs.event.AbstractEvent;
import lx.gs.logger.By;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiong on 2016/6/27.
 */
public class DailyGiftBuyHandler extends OperationalActivityHandler<DailyGift> {

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        return true;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        DailyGift conf = cast(entry);
        Set<Condition> conditions = new HashSet<>();
        conditions.add(conf.limit);
        conditions.add(conf.viplimit);
        conditions.add(conf.off);
        ErrorCode ret = FCondition.check(roleId, 1, By.Operator_Activity, ConfigId.OPERATION_ACTIVITY, entry.id, conditions);
        if(ret.err()){
            return ret;
        }
        return ErrorCode.OK;
    }

    @Override
    public void afterTakeReward(long roleId, ActivityEntry entry) {
        return;
    }
}

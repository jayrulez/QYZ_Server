package lx.gs.activity.operational;

import cfg.cmd.ConfigId;
import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.RechargeShop;
import common.ErrorCode;
import lx.gs.cmd.FCondition;
import lx.gs.event.AbstractEvent;
import lx.gs.logger.By;

/**
 * @author Jin Shuai
 */
public class RechargeShopHandler extends OperationalActivityHandler<RechargeShop> {

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        return true;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry){
        return FCondition.checkByReflection(roleId, entry.condition, 1, By.Operator_Activity, ConfigId.OPERATION_ACTIVITY, entry.id);
    }

    @Override
    public void afterTakeReward(long roleId, ActivityEntry entry) {
        return;
    }
}

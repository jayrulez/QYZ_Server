package lx.gs.activity.operational;

import cfg.bag.BagType;
import cfg.cmd.ConfigId;
import cfg.cmd.condition.Item;
import cfg.cmd.condition.LimitType;
import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.CollectItem;
import common.ErrorCode;
import lx.gs.bag.FBag;
import lx.gs.bag.ItemBag;
import lx.gs.cmd.FCondition;
import lx.gs.event.AbstractEvent;
import lx.gs.item.FItem;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;

/**
 * @author Jin Shuai
 */
public class CollectitemHandler extends OperationalActivityHandler<CollectItem> {

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        for (Item item : cast(entry).items.items) {
            ItemBag itemBag = FItem.getItemBag(roleId);
            if(itemBag.countItem(item.itemid) < item.amount){
                return false;
            }
        }
        return true;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry){
        ErrorCode errorCode = FCondition.checkA(roleId, cast(entry).daylimit, 1, By.Operator_Activity,ConfigId.OPERATION_ACTIVITY, entry.id);
        if(errorCode.err()){
            return errorCode;
        }
        ErrorCode errorCode2 = FCondition.checkA(roleId, cast(entry).totallimit, 1, By.Operator_Activity,ConfigId.OPERATION_ACTIVITY, entry.id);
        if(errorCode2.err()){
            return errorCode2;
        }
        for (Item item : cast(entry).items.items) {
            if(!FBag.deleteBindFirst(roleId, BagType.ITEM, item.itemid, item.amount, By.Operator_Activity)){
                return ErrorCode.ITEM_NUMBER_NOT_ENOUGH;
            }
        }
        return ErrorCode.OK;
    }

    @Override
    public void afterTakeReward(long roleId, ActivityEntry entry){
        if(FLimit.getLimitTimes(roleId, ConfigId.OPERATION_ACTIVITY, entry.id, LimitType.LIFELONG) >= cast(entry).totallimit.num){
            super.afterTakeReward(roleId, entry);
        }
    }

    @Override
    protected boolean canChangeCompleteStatus() {
        return true;
    }

}

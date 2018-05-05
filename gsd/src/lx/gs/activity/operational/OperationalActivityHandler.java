package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityCondition;
import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.OperationStatus;
import common.Bonus;
import common.ErrorCode;
import lx.gs.activity.ActivityModule;
import lx.gs.activity.FActivity;
import lx.gs.bonus.FBonus;
import lx.gs.event.AbstractEvent;
import lx.gs.logger.By;

/**
 * @author Jin Shuai
 */
public abstract class OperationalActivityHandler<C extends ActivityCondition> {

    public boolean checkNotify(long roleId, ActivityEntry entry){
        return checkNotify(roleId, entry, null);
    }

    public boolean checkNotify(long roleId, ActivityEntry entry, AbstractEvent event){
        if(onlyTriggerByEvent() && event == null){
            return false;
        }
        int currStatus = FActivity.currStatus(roleId, getOperationalType(entry.id), entry.id);
        if(currStatus == OperationStatus.NOT_COMPLETE
                || (currStatus == OperationStatus.COMPLETE && canChangeCompleteStatus())){
            int newStatus = checkCondition(roleId, entry, event) ? OperationStatus.COMPLETE : OperationStatus.NOT_COMPLETE;
            if(currStatus != newStatus){
                FActivity.changeStatus(roleId, getOperationalType(entry.id), entry.id, newStatus);
                FActivity.sendNotify(roleId, getOperationalType(entry.id), entry.id, newStatus);
            }
        }
        return true;
    }

    /** 兑换类活动、每日活动 需要重写 */
    protected boolean canChangeCompleteStatus(){
        return false;
    }

    /** 兑换类活动需要重写此方法，判断是否已经全部领取完毕 */
    public void afterTakeReward(long roleId, ActivityEntry entry){
        FActivity.changeStatus(roleId, getOperationalType(entry.id), entry.id, OperationStatus.GETREWARD);
    }

    public int getOperationalType(int entryId){
        return ActivityModule.INSTACNE.getType(entryId);
    }

    public abstract boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event);

    public boolean takeReward(long roleid, ActivityEntry activityEntry){
        if(FBonus.addBonus(roleid, activityEntry.reward, Bonus.BindType.BIND, By.Operator_Activity)){
            afterTakeReward(roleid, activityEntry);
            return true;
        }
        return false;
    }

    public abstract ErrorCode handleCondition(long roleId, ActivityEntry entry);

    @SuppressWarnings("unchecked")
    public C cast(ActivityEntry entry) {
        return (C) entry.condition;
    }

    /** 需要存储数据的活动要重写方法添加存储逻辑，并返回true */
    public boolean consumeEvent(AbstractEvent event, ActivityEntry entry){
        return false;
    }

    /**
     * 是否只由事件驱动check，如果是，则需要重写此方法，并保证event不为空
     * 如果不需要事件驱动，或者登陆时需要check，就不用管
     * @return
     */
    protected boolean onlyTriggerByEvent(){
        return false;
    }

    /** 每日活动重写方法添加逻辑 */
    public void onDayOver(long roleId, ActivityEntry entry){}

    public boolean isDailyActivity(){
        return false;
    }
}

package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.TalismanAwake;
import common.ErrorCode;
import lx.gs.event.AbstractEvent;
import lx.gs.event.TalismanAwakeLevelUpEvent;

/**
 * @author Jin Shuai
 */
public class TalismanAwakeHandler extends OperationalActivityHandler<TalismanAwake> {

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        TalismanAwakeLevelUpEvent event1 = event.cast();
        return event1.newLevel >= cast(entry).num;
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

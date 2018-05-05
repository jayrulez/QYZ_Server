package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.PetMaxAwake;
import common.ErrorCode;
import lx.gs.event.AbstractEvent;
import lx.gs.event.PetAwakeUpEvent;

/**
 * @author Jin Shuai
 */
public class PetMaxAwakeHandler extends OperationalActivityHandler<PetMaxAwake> {
    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        PetAwakeUpEvent petAwakeUpEvent = event.cast();
        return petAwakeUpEvent.newLevel >= cast(entry).num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }

    @Override
    protected boolean onlyTriggerByEvent() {
        return true;
    }
}

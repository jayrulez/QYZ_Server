package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.KillWorldBoss;
import common.ErrorCode;
import lx.gs.activity.FActivity;
import lx.gs.event.AbstractEvent;
import lx.gs.event.EventType;
import xbean.RoleActivityRecord;

/**
 * @author Jin Shuai
 */
public class KillWorldBossHandler extends OperationalActivityHandler<KillWorldBoss> {
    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        return FActivity.getActivityDataByType(roleId, getOperationalType(entry.id)).getKillworldboss() >= cast(entry).num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }

    @Override
    public boolean consumeEvent(AbstractEvent event, ActivityEntry entry) {
        if(event.eventType == EventType.KILL_WORLDBOSS){
            RoleActivityRecord record = FActivity.getActivityDataByType(event.roleId, getOperationalType(entry.id));
            record.setKillworldboss(record.getKillworldboss() + 1);
            return true;
        }
        return false;
    }
}

package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.JadeLevel;
import common.ErrorCode;
import lx.gs.event.AbstractEvent;
import lx.gs.jade.FJade;

/**
 * @author Jin Shuai
 */
public class JadeLevelHandler extends OperationalActivityHandler<JadeLevel> {
    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        return FJade.getRoleJadeInfo(roleId).getLevel() >= cast(entry).num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }
}

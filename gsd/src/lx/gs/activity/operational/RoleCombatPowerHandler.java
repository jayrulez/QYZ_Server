package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.RoleCombatPower;
import common.ErrorCode;
import lx.gs.event.AbstractEvent;
import lx.gs.role.FRoleAttr;

/**
 * @author Jin Shuai
 */
public class RoleCombatPowerHandler extends OperationalActivityHandler<RoleCombatPower> {

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        return FRoleAttr.getRoleCombatPower(roleId) >= cast(entry).num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry){
        return ErrorCode.OK;
    }

}

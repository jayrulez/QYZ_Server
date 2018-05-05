package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.ClimbTower;
import common.ErrorCode;
import lx.gs.event.AbstractEvent;
import lx.gs.map.FMap;

/**
 * @author Jin Shuai
 */
public class ClimbTowerHandler extends OperationalActivityHandler<ClimbTower> {
    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        return FMap.getEctype(roleId).getClimbtowers().values().stream()
                .anyMatch(climbTowerInfo -> climbTowerInfo.getMaxfloorid() >= cast(entry).num);
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }

}

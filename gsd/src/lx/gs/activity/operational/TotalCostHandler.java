package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.TotalCost;
import common.ErrorCode;
import lx.gs.activity.FActivity;
import lx.gs.event.AbstractEvent;

/**
 * Created by xiong on 2016/6/27.
 */
public class TotalCostHandler extends OperationalActivityHandler<TotalCost>{

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        xbean.RoleActivityRecord datas =FActivity.getActivityDataByType(roleId, getOperationalType(entry.id));
        return datas.getTotalcostyuanbbao() >= cast(entry).num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }
}

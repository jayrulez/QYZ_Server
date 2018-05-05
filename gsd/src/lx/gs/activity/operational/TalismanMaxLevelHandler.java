package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.TalismanMaxLevel;
import common.ErrorCode;
import lx.gs.event.AbstractEvent;
import lx.gs.talisman.FTalisman;
import xbean.Talisman;

import java.util.List;

/**
 * @author Jin Shuai
 */
public class TalismanMaxLevelHandler extends OperationalActivityHandler<TalismanMaxLevel> {
    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        List<Talisman> list = FTalisman.getTalismanBag(roleId).getItems();
        list.addAll(FTalisman.getBodyTalismanBag(roleId).getItems());
        return list.stream().anyMatch(t -> t.getNormallevel() >= cast(entry).num) ;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }
}

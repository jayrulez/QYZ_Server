package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.TalismanQuality;
import common.ErrorCode;
import lx.gs.event.AbstractEvent;
import lx.gs.talisman.FTalisman;
import xbean.Talisman;

import java.util.List;

/**
 * @author Jin Shuai
 */
public class TalismanQualityHandler extends OperationalActivityHandler<TalismanQuality> {

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        List<Talisman> list = FTalisman.getTalismanBag(roleId).getItems();
        list.addAll(FTalisman.getBodyTalismanBag(roleId).getItems());
        return list.stream()
                .filter(t -> FTalisman.getModelById(t.getModelid()).quality == cast(entry).qulity)
                .count() >= cast(entry).num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry){
        return ErrorCode.OK;
    }

}

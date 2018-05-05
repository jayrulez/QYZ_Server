package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.EquipAnnealTotal;
import common.ErrorCode;
import lx.gs.equip.FEquip;
import lx.gs.event.AbstractEvent;
import xbean.Equip;

import java.util.List;

/**
 * Created by xiong on 2016/6/27.
 */
public class EquipAnnelTotalHandler extends OperationalActivityHandler<EquipAnnealTotal>{

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        List<Equip> list = FEquip.getBodyBag(roleId).getItems();
        int totalAnnel = list.stream().filter(e -> e.getPosition() <= 4).mapToInt(e -> e.getNormalequip().getAnneallevel()).sum();
        return totalAnnel >= cast(entry).num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }
}

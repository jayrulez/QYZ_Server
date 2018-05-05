package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.EquipPerfuseTotal;
import common.ErrorCode;
import lx.gs.equip.FEquip;
import lx.gs.event.AbstractEvent;
import xbean.Equip;

import java.util.List;

/**
 * Created by xiong on 2016/6/27.
 */
public class EquipPerfuseTotalHandler extends OperationalActivityHandler<EquipPerfuseTotal> {

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        List<Equip> list = FEquip.getBodyBag(roleId).getItems();
        int totalPerfuse = list.stream().filter(e -> e.getPosition() <= 4).mapToInt(e -> e.getNormalequip().getPerfuselevel()).sum();
        return totalPerfuse >= cast(entry).num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }
}

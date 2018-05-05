package lx.gs.activity.operational;

import cfg.equip.Equip;
import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.EquipQuality;
import common.ErrorCode;
import lx.gs.equip.FEquip;
import lx.gs.event.AbstractEvent;


/**
 * Created by xiong on 2016/6/27.
 */
public class EquipQualityHandler extends OperationalActivityHandler<EquipQuality> {

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        EquipQuality e = cast(entry);
        return FEquip.getBodyBag(roleId).getItems().stream()
                .filter(equip -> {
                    Equip model = FEquip.getEquipModel(equip);
                    return FEquip.isNormalEquip(model) && model.level == e.level
                            && model.quality == e.qulity;
                })
                .count() >= e.num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }
}

package lx.gs.activity.operational;

import cfg.equip.Equip;
import cfg.operationalactivity.AccessoryQuality;
import cfg.operationalactivity.ActivityEntry;
import common.ErrorCode;
import lx.gs.equip.FEquip;
import lx.gs.event.AbstractEvent;

/**
 * @author Jin Shuai
 */
public class AccessoryQualityHandler extends OperationalActivityHandler<AccessoryQuality> {
    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        AccessoryQuality e = cast(entry);
        return FEquip.getBodyBag(roleId).getItems().stream()
                .filter(equip -> {
                    Equip model = FEquip.getEquipModel(equip);
                    return FEquip.isAccessory(model) && model.level == e.level
                            && model.quality == e.qulity;
                })
                .count() >= e.num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }
}

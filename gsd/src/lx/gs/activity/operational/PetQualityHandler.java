package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.PetQuality;
import common.ErrorCode;
import lx.gs.event.AbstractEvent;
import lx.gs.pet.FPet;

/**
 * @author Jin Shuai
 */
public class PetQualityHandler extends OperationalActivityHandler<PetQuality> {

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        return FPet.get(roleId).getPetmap().values().stream()
                .filter(pet -> FPet.getModelById(pet.getModelid()).basiccolor == cast(entry).qulity)
                .count() >= cast(entry).num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry){
        return ErrorCode.OK;
    }

}

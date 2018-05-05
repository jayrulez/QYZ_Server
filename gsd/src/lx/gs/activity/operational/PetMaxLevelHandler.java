package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.PetMaxLevel;
import common.ErrorCode;
import lx.gs.event.AbstractEvent;
import lx.gs.pet.FPet;

/**
 * @author Jin Shuai
 */
public class PetMaxLevelHandler extends OperationalActivityHandler<PetMaxLevel> {
    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        return FPet.get(roleId).getPetmap().values().stream()
                .anyMatch(pet -> pet.getLevel() >= cast(entry).num);
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }
}

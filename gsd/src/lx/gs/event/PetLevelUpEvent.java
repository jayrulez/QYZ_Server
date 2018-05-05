package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class PetLevelUpEvent extends AbstractEvent {
    public PetLevelUpEvent(long roleId) {
        super(roleId, EventType.PET_LEVEL_UP);
    }
}

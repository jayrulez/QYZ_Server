package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class PetStarUpEvent extends AbstractEvent {
    public PetStarUpEvent(long roleId) {
        super(roleId, EventType.PET_STAR_UP);
    }
}

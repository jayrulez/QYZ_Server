package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class AddPetEvent extends AbstractEvent{

    public AddPetEvent(long roleId) {
        super(roleId, EventType.ADD_PET);
    }
}

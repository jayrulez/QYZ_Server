package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class AddItemEvent extends AbstractEvent {
    public AddItemEvent(long roleId) {
        super(roleId, EventType.ADD_ITEM);
    }
}

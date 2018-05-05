package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class DeleteItemEvent extends AbstractEvent {
    public DeleteItemEvent(long roleId) {
        super(roleId, EventType.DELETE_ITEM);
    }
}

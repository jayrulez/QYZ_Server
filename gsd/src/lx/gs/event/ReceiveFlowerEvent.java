package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class ReceiveFlowerEvent extends AbstractEvent{

    public final int currVal;
    public ReceiveFlowerEvent(long roleId, int currVal) {
        super(roleId, EventType.RECEIVE_FLOWER);
        this.currVal = currVal;
    }
}

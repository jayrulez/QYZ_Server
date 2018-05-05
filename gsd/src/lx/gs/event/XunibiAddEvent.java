package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class XunibiAddEvent extends AbstractEvent{

    public final long currVal;
    public XunibiAddEvent(long roleId, long currVal) {
        super(roleId, EventType.XUNIBI_ADD);
        this.currVal = currVal;
    }
}

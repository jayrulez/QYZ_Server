package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class TalismanAddEvent  extends AbstractEvent{

    public TalismanAddEvent(long roleId) {
        super(roleId, EventType.TALISMAN_ADD);
    }
}

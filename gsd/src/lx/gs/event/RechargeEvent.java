package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class RechargeEvent extends AbstractEvent {
    public RechargeEvent(long roleId) {
        super(roleId, EventType.RECHARGE);
    }
}

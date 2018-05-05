package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class TalismanLevelUpEvent extends AbstractEvent {
    public TalismanLevelUpEvent(long roleId) {
        super(roleId, EventType.TALISMAN_LEVEL_UP);
    }
}

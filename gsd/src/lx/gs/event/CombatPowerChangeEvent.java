package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class CombatPowerChangeEvent extends AbstractEvent{

    public final int currVal;
    public CombatPowerChangeEvent(long roleId, int currVal) {
        super(roleId, EventType.COMBATPOWER_CHANGE);
        this.currVal = currVal;
    }
}

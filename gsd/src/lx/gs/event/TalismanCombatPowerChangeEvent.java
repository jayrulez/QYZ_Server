package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class TalismanCombatPowerChangeEvent extends AbstractEvent{

    public final int currVal;
    public TalismanCombatPowerChangeEvent(long roleId, int currVal) {
        super(roleId, EventType.TALISMAN_COMBATPOWER_CHANGE);
        this.currVal = currVal;
    }
}

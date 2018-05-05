package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class PetCombatPowerChangeEvent extends AbstractEvent{

    public final int currVal;
    public PetCombatPowerChangeEvent(long roleId, int currVal) {
        super(roleId, EventType.PET_COMBATPOWER_CHANGE);
        this.currVal = currVal;
    }
}

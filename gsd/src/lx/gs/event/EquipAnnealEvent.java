package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class EquipAnnealEvent extends AbstractEvent {
    public final int modelId;
    public final int newLevel;

    public EquipAnnealEvent(long roleId, int modelId, int newLevel) {
        super(roleId, EventType.EQUIP_ANNEAL);
        this.newLevel = newLevel;
        this.modelId = modelId;
    }
}

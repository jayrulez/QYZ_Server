package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class EquipOnBodyEvent extends AbstractEvent {
    public EquipOnBodyEvent(long roleId) {
        super(roleId, EventType.EQUIP_ON_BODY);
    }
}

package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class JadeLevelUpEvent extends AbstractEvent {
    public JadeLevelUpEvent(long roleId) {
        super(roleId, EventType.JADE_LEVEL_UP);
    }
}

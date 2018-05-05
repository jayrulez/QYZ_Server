package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class KillWorldBossEvent extends AbstractEvent {
    public KillWorldBossEvent(long roleId) {
        super(roleId, EventType.KILL_WORLDBOSS);
    }
}

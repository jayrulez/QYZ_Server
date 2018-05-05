package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class ArenaWinEvent extends AbstractEvent {

    public ArenaWinEvent(long roleId) {
        super(roleId, EventType.ARENA_WIN);
    }
}

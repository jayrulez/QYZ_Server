package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class TeamSpeedWinEvent extends AbstractEvent {
    public TeamSpeedWinEvent(long roleId) {
        super(roleId, EventType.TEAM_SPEED_WIN);
    }
}

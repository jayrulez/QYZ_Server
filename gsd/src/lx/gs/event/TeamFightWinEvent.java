package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class TeamFightWinEvent extends AbstractEvent {
    public TeamFightWinEvent(long roleId) {
        super(roleId, EventType.TEAM_FIGHT_WIN);
    }
}

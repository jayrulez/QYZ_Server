package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class LevelUpEvent extends AbstractEvent{
    public final int currLevel;

    public LevelUpEvent(long roleId, int currLevel) {
        super(roleId, EventType.LEVEL_UP);
        this.currLevel = currLevel;
    }
}

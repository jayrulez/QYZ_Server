package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class TalismanStarLevelUpEvent  extends AbstractEvent{
    public final int modelId;
    public final int currStarLevel;

    public TalismanStarLevelUpEvent(long roleId, int modelId, int currStarLevel) {
        super(roleId, EventType.TALISMAN_STAR_LEVEL_UP);
        this.modelId = modelId;
        this.currStarLevel = currStarLevel;
    }
}

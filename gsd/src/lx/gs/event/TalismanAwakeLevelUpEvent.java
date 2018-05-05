package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class TalismanAwakeLevelUpEvent  extends AbstractEvent{
    public final int newLevel;
    public final int modelId;

    public TalismanAwakeLevelUpEvent(long roleId, int newLevel, int modelId) {
        super(roleId, EventType.TALISMAN_AWAKE_LEVEL_UP);
        this.newLevel = newLevel;
        this.modelId = modelId;
    }
}

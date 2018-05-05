package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class PetAwakeUpEvent extends AbstractEvent {
    public final int newLevel;
    public final int modelId;
    public final boolean isFightPet;

    public PetAwakeUpEvent(long roleId,  int newLevel, int modelId, boolean isFightPet) {
        super(roleId, EventType.PET_AWAKE_UP);
        this.newLevel = newLevel;
        this.modelId = modelId;
        this.isFightPet = isFightPet;
    }
}

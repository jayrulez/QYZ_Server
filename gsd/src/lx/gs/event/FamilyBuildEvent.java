package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class FamilyBuildEvent extends AbstractEvent{

    public final long familyId;
    public final int currVal;

    public FamilyBuildEvent(long roleId, long familyId, int currVal) {
        super(roleId, EventType.FAMILY_BUILD);
        this.familyId = familyId;
        this.currVal = currVal;
    }
}

package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class FamilyDisbandEvent extends AbstractEvent {
    public final long familyId;
    public final String familyFullName;
    public FamilyDisbandEvent(long roleId, long familyId, String familyFullName) {
        super(roleId, EventType.FAMILY_DISBAND);
        this.familyId = familyId;
        this.familyFullName = familyFullName;
    }
}

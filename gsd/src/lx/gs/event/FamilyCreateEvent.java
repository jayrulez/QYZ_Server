package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class FamilyCreateEvent extends AbstractEvent {
    public final long familyId;
    public final String familyFullName;
    public FamilyCreateEvent(long roleId, long familyId, String familyFullName) {
        super(roleId, EventType.FAMILY_CREATE);
        this.familyId = familyId;
        this.familyFullName = familyFullName;
    }
}

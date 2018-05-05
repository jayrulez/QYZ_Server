package lx.gs.event;

/**
 * @author Jin Shuai
 */
public abstract class AbstractEvent {
    public final int eventType;
    public final long roleId;

    public AbstractEvent(long roleId, int eventType) {
        this.eventType = eventType;
        this.roleId = roleId;
    }

    @SuppressWarnings("unchecked")
    public <E extends AbstractEvent> E cast(){
        return (E) this;
    }

}

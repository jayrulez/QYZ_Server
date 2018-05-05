package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class StoryNoteUpEvent extends AbstractEvent {
    public StoryNoteUpEvent(long roleId) {
        super(roleId, EventType.STORYNOTE_LEVEL_UP);
    }
}

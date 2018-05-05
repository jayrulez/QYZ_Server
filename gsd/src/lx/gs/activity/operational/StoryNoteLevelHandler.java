package lx.gs.activity.operational;

import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.StoryNoteLevel;
import common.ErrorCode;
import lx.gs.event.AbstractEvent;
import lx.gs.storynote.FStoryNote;

/**
 * @author Jin Shuai
 */
public class StoryNoteLevelHandler extends OperationalActivityHandler<StoryNoteLevel> {
    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {
        return FStoryNote.get(roleId).getChapters().keySet().stream().anyMatch(chapter -> chapter>= cast(entry).num);
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry) {
        return ErrorCode.OK;
    }
}

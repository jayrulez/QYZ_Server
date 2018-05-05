package lx.gs.task.condition;

import xbean.RoleTask;
import xbean.TaskData;

public final class LevelLimit extends Condition {
	private final int minLevel;
	private final int maxLevel;
	public LevelLimit(int minLevel, int maxLevel) {
		this.minLevel = minLevel > 0 ? minLevel : Integer.MIN_VALUE;
		this.maxLevel = maxLevel > 0 ? maxLevel : Integer.MAX_VALUE;
	}

	@Override
	public boolean check(RoleTask info, TaskData data) {
		final int level = xtable.Roleinfos.get(info.getRoleid()).getLevel();
		return level >= minLevel && level <= maxLevel;
	}

	@Override
	public void onEvent(RoleTask info, TaskData data, ConditionParam param) {

	}

	@Override
	public int getEventId() {
		return EventIds.NONE;
	}

}

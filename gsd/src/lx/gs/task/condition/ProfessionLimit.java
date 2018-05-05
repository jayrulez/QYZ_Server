package lx.gs.task.condition;

import xbean.RoleTask;
import xbean.TaskData;

public final class ProfessionLimit extends Condition {
	private final int profession;
	public ProfessionLimit(int profession) {
		this.profession = profession;
	}
	@Override
	public boolean check(RoleTask info, TaskData data) {
		return xtable.Roleinfos.get(info.getRoleid()).getProfession() == profession;
	}

	@Override
	public void onEvent(RoleTask info, TaskData data, ConditionParam param) {

	}

	@Override
	public int getEventId() {
		return EventIds.NONE;
	}

}

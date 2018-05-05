package lx.gs.task.condition;

import cfg.task.ItemInfo;
import xbean.RoleTask;
import xbean.TaskData;

import java.util.HashMap;
import java.util.Map;

public final class ItemLimit extends Condition {
	private final Map<Integer, ItemInfo> requireItems;
	public ItemLimit(Map<Integer, ItemInfo> requireItems) {
		this.requireItems = new HashMap<>(requireItems);
	}

	@Override
	public boolean check(RoleTask info, TaskData data) {
		return false;
	}

	@Override
	public void onEvent(RoleTask info, TaskData data, ConditionParam param) {

	}

	@Override
	public int getEventId() {
		return EventIds.NONE;
	}

}

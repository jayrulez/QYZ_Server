package lx.gs.task.condition;

import xbean.RoleTask;
import xbean.TaskData;

import java.util.Map;

public final class KillMonster extends Condition {
	private final int monsterid;
	private final int killNum;
	public KillMonster(int monsterid, int killNum) {
		this.monsterid = monsterid;
		this.killNum = killNum;
	}
	@Override
	public boolean check(RoleTask info, TaskData data) {
		final Integer value = data.getCounter().get(monsterid);
		return value != null && value >= killNum;
	}

	@Override
	public void onEvent(RoleTask info, TaskData data, ConditionParam param) {
		if(param.id == monsterid) {
			final Map<Integer, Integer> counter = data.getCounter();
            final int oldValue = counter.getOrDefault(monsterid, 0);
            if(oldValue >= killNum) return;
			final int newValue = oldValue + param.num;
			counter.put(monsterid, newValue);
			xdb.Trace.debug("Task.Condition.KillMonster change. roleid:{}  monsterid:{} add:{} newvalue:{}",
				info.getRoleid(), monsterid, param.num, newValue);
		}
	}

	@Override
	public int getEventId() {
		return EventIds.MONSTER;
	}

}

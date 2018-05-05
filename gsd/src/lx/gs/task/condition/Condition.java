package lx.gs.task.condition;

public abstract class Condition {
	public final static class ConditionParam {
		public int eventid;
		public int id;
		public int num;
	}

	public abstract boolean check(xbean.RoleTask info, xbean.TaskData data);
	public abstract void onEvent(xbean.RoleTask info, xbean.TaskData data, ConditionParam param);

	public abstract int getEventId();
}

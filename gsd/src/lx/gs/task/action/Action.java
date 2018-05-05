package lx.gs.task.action;

import lx.gs.task.Task;

public abstract class Action {
	public abstract void process(xbean.RoleTask info, Task task, xbean.TaskData data);
}

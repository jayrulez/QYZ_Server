package lx.gs.task.action;

import lx.gs.task.Task;

public final class LogAccept extends Action {

	@Override
	public void process(xbean.RoleTask info, Task task, xbean.TaskData data) {
		xdb.Trace.info("Script.LogAccept roleid:{} taskid:{}", info.getRoleid(), task.getTaskid());
	}

}

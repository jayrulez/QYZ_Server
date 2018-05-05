package lx.gs.task.action;

import lx.gs.task.FTask;
import lx.gs.task.Task;

public final class VariableClear extends Action {
	private final int varname;
	public VariableClear(int varname) {
		this.varname = varname;
	}
	
	@Override
	public void process(xbean.RoleTask info, Task task, xbean.TaskData data) {
		FTask.clearVariable(info, varname);
	}

}

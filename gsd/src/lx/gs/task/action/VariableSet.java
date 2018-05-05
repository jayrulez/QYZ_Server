package lx.gs.task.action;

import lx.gs.task.FTask;
import lx.gs.task.Task;

public final class VariableSet extends Action {
	private final int varname;
	private final int value;
	public VariableSet(int varname, int value) {
		this.varname = varname;
		this.value = value;
	}
	@Override
	public void process(xbean.RoleTask info, Task task, xbean.TaskData data) {
		FTask.setVariable(info, varname, value);
	}
}

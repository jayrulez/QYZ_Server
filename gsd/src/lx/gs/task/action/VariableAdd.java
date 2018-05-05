package lx.gs.task.action;

import lx.gs.task.FTask;
import lx.gs.task.Task;

public final class VariableAdd extends Action {
	private final int varid;
	private final int add;
	public VariableAdd(int varid, int add) {
		this.varid = varid;
		this.add = add;
	}
	
	@Override
	public void process(xbean.RoleTask info, Task task, xbean.TaskData data) {
		FTask.addVariable(info, varid, add);
	}

}

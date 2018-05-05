package cfg.cmd.condition;
public final class CompleteTask extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -1971210385;
	final public int getTypeId() { return TYPEID; }
	public final int taskid;
	public CompleteTask(cfg.DataStream fs) {
		super(fs);
		this.taskid = fs.getInt();
	}
}
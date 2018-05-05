package cfg.cmd.condition;
public final class AcceptTask extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -1823028226;
	final public int getTypeId() { return TYPEID; }
	public final int taskid;
	public AcceptTask(cfg.DataStream fs) {
		super(fs);
		this.taskid = fs.getInt();
	}
}
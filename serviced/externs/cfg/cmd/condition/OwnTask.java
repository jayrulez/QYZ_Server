package cfg.cmd.condition;
public final class OwnTask extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 1577705082;
	final public int getTypeId() { return TYPEID; }
	public final int taskid;
	public OwnTask(cfg.DataStream fs) {
		super(fs);
		this.taskid = fs.getInt();
	}
}
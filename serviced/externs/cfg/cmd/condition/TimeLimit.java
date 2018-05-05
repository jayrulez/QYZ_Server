package cfg.cmd.condition;
public final class TimeLimit extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 553755005;
	final public int getTypeId() { return TYPEID; }
	public final int limittype;
	public final int starttime;
	public final int endtime;
	public TimeLimit(cfg.DataStream fs) {
		super(fs);
		this.limittype = fs.getInt();
		this.starttime = fs.getInt();
		this.endtime = fs.getInt();
	}
}
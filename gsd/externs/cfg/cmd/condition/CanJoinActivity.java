package cfg.cmd.condition;
public final class CanJoinActivity extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 1362558872;
	final public int getTypeId() { return TYPEID; }
	public final int activitytype;
	public CanJoinActivity(cfg.DataStream fs) {
		super(fs);
		this.activitytype = fs.getInt();
	}
}
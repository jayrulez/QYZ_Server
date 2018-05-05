package cfg.operationalactivity;
public final class DailyRecharge extends cfg.operationalactivity.ActivityCondition {
	public final static int TYPEID = 298331753;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.DayLimit daylimit;
	public final int num;
	public DailyRecharge(cfg.DataStream fs) {
		super(fs);
		this.daylimit = new cfg.cmd.condition.DayLimit(fs);
		this.num = fs.getInt();
	}
}
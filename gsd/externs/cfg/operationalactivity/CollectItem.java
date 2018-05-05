package cfg.operationalactivity;
public final class CollectItem extends cfg.operationalactivity.ActivityCondition {
	public final static int TYPEID = -408727514;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.DayLimit daylimit;
	public final cfg.cmd.condition.Limit totallimit;
	public final cfg.cmd.condition.Items items;
	public CollectItem(cfg.DataStream fs) {
		super(fs);
		this.daylimit = new cfg.cmd.condition.DayLimit(fs);
		this.totallimit = new cfg.cmd.condition.Limit(fs);
		this.items = new cfg.cmd.condition.Items(fs);
	}
}
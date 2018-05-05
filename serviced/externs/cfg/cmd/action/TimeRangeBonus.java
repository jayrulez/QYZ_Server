package cfg.cmd.action;
public final class TimeRangeBonus extends cfg.cmd.action.Bonus {
	public final static int TYPEID = 425162665;
	final public int getTypeId() { return TYPEID; }
	public final cfg.common.DateTimeRange openrange;
	public final cfg.cmd.action.Bonus bonus;
	public TimeRangeBonus(cfg.DataStream fs) {
		super(fs);
		this.openrange = new cfg.common.DateTimeRange(fs);
		this.bonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}
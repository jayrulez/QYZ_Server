package cfg.lottery;
public final class Exchange extends cfg.CfgObject {
	public final static int TYPEID = -1835014254;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.cmd.action.Item item;
	public final cfg.cmd.condition.Currency requirecurrency;
	public final cfg.cmd.condition.DayLimit daylimit;
	public final String desc;
	public Exchange(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.item = new cfg.cmd.action.Item(fs);
		this.requirecurrency = new cfg.cmd.condition.Currency(fs);
		this.daylimit = new cfg.cmd.condition.DayLimit(fs);
		this.desc = fs.getString();
	}
}
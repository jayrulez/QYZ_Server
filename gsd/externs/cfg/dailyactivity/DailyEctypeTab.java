package cfg.dailyactivity;
public final class DailyEctypeTab extends cfg.CfgObject {
	public final static int TYPEID = 1896769908;
	final public int getTypeId() { return TYPEID; }
	public final int order;
	public final String name;
	public final int ectypetype;
	public DailyEctypeTab(cfg.DataStream fs) {
		this.order = fs.getInt();
		this.name = fs.getString();
		this.ectypetype = fs.getInt();
	}
}
package cfg.dailyactivity;
public final class DailyActivityTab extends cfg.CfgObject {
	public final static int TYPEID = -612713955;
	final public int getTypeId() { return TYPEID; }
	public final int order;
	public final String name;
	public final String label;
	public final String tabindex;
	public DailyActivityTab(cfg.DataStream fs) {
		this.order = fs.getInt();
		this.name = fs.getString();
		this.label = fs.getString();
		this.tabindex = fs.getString();
	}
}
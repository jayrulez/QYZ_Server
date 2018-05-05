package cfg.dailyactivity;
public final class WeekendActivityTab extends cfg.CfgObject {
	public final static int TYPEID = -185693425;
	final public int getTypeId() { return TYPEID; }
	public final int order;
	public final String name;
	public final String label;
	public final String tabindex;
	public WeekendActivityTab(cfg.DataStream fs) {
		this.order = fs.getInt();
		this.name = fs.getString();
		this.label = fs.getString();
		this.tabindex = fs.getString();
	}
}
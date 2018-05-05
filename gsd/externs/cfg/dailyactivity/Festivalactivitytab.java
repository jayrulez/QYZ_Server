package cfg.dailyactivity;
public final class Festivalactivitytab extends cfg.CfgObject {
	public final static int TYPEID = -1607147586;
	final public int getTypeId() { return TYPEID; }
	public final int order;
	public final String name;
	public final String label;
	public final String tabindex;
	public Festivalactivitytab(cfg.DataStream fs) {
		this.order = fs.getInt();
		this.name = fs.getString();
		this.label = fs.getString();
		this.tabindex = fs.getString();
	}
}
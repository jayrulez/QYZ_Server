package cfg.ui;
public final class Tab extends cfg.CfgObject {
	public final static int TYPEID = -1507360283;
	final public int getTypeId() { return TYPEID; }
	public final String tabname;
	public final boolean hide;
	public Tab(cfg.DataStream fs) {
		this.tabname = fs.getString();
		this.hide = fs.getBool();
	}
}
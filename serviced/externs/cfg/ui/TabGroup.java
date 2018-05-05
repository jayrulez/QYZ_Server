package cfg.ui;
public final class TabGroup extends cfg.CfgObject {
	public final static int TYPEID = -968561766;
	final public int getTypeId() { return TYPEID; }
	public final String tabgroupname;
	public final String tabgroupicon;
	public final int backgroundtype;
	public final int conid;
	public final java.util.List<cfg.ui.Tab> tabs = new java.util.ArrayList<>();
	public TabGroup(cfg.DataStream fs) {
		this.tabgroupname = fs.getString();
		this.tabgroupicon = fs.getString();
		this.backgroundtype = fs.getInt();
		this.conid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.tabs.add(new cfg.ui.Tab(fs));
		}
	}
}
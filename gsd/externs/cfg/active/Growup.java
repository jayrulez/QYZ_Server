package cfg.active;
public final class Growup extends cfg.CfgObject {
	public final static int TYPEID = 644701676;
	final public int getTypeId() { return TYPEID; }
	public final int systemid;
	public final int systemname;
	public final String name;
	public final String desc;
	public final String icon;
	public final int growtype;
	public final String uientry;
	public final int uitabindex;
	public final int uitabindex02;
	public final boolean showbasedialog;
	public Growup(cfg.DataStream fs) {
		this.systemid = fs.getInt();
		this.systemname = fs.getInt();
		this.name = fs.getString();
		this.desc = fs.getString();
		this.icon = fs.getString();
		this.growtype = fs.getInt();
		this.uientry = fs.getString();
		this.uitabindex = fs.getInt();
		this.uitabindex02 = fs.getInt();
		this.showbasedialog = fs.getBool();
	}
}
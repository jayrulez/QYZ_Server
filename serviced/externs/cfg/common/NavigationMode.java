package cfg.common;
public final class NavigationMode extends cfg.CfgObject {
	public final static int TYPEID = -109266352;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public NavigationMode(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
	}
}
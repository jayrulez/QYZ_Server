package cfg.ui;
public final class nextfunctiontips extends cfg.CfgObject {
	public final static int TYPEID = 1599868179;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final String icon;
	public final int conid;
	public final String coniddesc;
	public final String functiondesc;
	public nextfunctiontips(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.icon = fs.getString();
		this.conid = fs.getInt();
		this.coniddesc = fs.getString();
		this.functiondesc = fs.getString();
	}
}
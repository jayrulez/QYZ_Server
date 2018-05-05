package cfg.controller;
public final class EctypeController extends cfg.CfgObject {
	public final static int TYPEID = 545338428;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final String introcuction;
	public final String owner;
	public final boolean initalstatus;
	public final float duration;
	public final int waitbeforeopen;
	public final int waitbeforeclose;
	public EctypeController(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.introcuction = fs.getString();
		this.owner = fs.getString();
		this.initalstatus = fs.getBool();
		this.duration = fs.getFloat();
		this.waitbeforeopen = fs.getInt();
		this.waitbeforeclose = fs.getInt();
	}
}
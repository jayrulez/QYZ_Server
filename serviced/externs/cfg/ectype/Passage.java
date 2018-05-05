package cfg.ectype;
public final class Passage extends cfg.CfgObject {
	public final static int TYPEID = -2104230174;
	final public int getTypeId() { return TYPEID; }
	public final int curveid;
	public final int linkedlayout;
	public final int id;
	public Passage(cfg.DataStream fs) {
		this.curveid = fs.getInt();
		this.linkedlayout = fs.getInt();
		this.id = fs.getInt();
	}
}
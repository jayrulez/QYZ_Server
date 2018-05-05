package cfg.common;
public final class BroadcastType extends cfg.CfgObject {
	public final static int TYPEID = 698647554;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public BroadcastType(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
	}
}
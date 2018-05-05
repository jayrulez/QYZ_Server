package cfg.common;
public final class CountType extends cfg.CfgObject {
	public final static int TYPEID = 49766608;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public CountType(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
	}
}
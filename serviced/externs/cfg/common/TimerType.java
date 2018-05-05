package cfg.common;
public final class TimerType extends cfg.CfgObject {
	public final static int TYPEID = -1248719866;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public TimerType(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
	}
}
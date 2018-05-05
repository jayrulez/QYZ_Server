package cfg.family;
public final class OpenTime extends cfg.CfgObject {
	public final static int TYPEID = -1057859049;
	final public int getTypeId() { return TYPEID; }
	public final int day;
	public final int hour;
	public final int minute;
	public OpenTime(cfg.DataStream fs) {
		this.day = fs.getInt();
		this.hour = fs.getInt();
		this.minute = fs.getInt();
	}
}
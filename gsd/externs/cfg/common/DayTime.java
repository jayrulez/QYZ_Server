package cfg.common;
public final class DayTime extends cfg.CfgObject {
	public final static int TYPEID = -2080172496;
	final public int getTypeId() { return TYPEID; }
	public final int hour;
	public final int minute;
	public final int second;
	public DayTime(cfg.DataStream fs) {
		this.hour = fs.getInt();
		this.minute = fs.getInt();
		this.second = fs.getInt();
	}
}
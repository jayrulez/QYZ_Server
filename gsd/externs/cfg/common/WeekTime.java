package cfg.common;
public final class WeekTime extends cfg.CfgObject {
	public final static int TYPEID = 1690863642;
	final public int getTypeId() { return TYPEID; }
	public final int weekday;
	public final int hour;
	public final int minute;
	public final int second;
	public WeekTime(cfg.DataStream fs) {
		this.weekday = fs.getInt();
		this.hour = fs.getInt();
		this.minute = fs.getInt();
		this.second = fs.getInt();
	}
}
package cfg.ectype;
public final class TimeLimit extends cfg.CfgObject {
	public final static int TYPEID = 1731910530;
	final public int getTypeId() { return TYPEID; }
	public final int day;
	public final int hour;
	public final int minute;
	public final int second;
	public TimeLimit(cfg.DataStream fs) {
		this.day = fs.getInt();
		this.hour = fs.getInt();
		this.minute = fs.getInt();
		this.second = fs.getInt();
	}
}
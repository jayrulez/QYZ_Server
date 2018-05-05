package cfg.common;
public final class DateTime extends cfg.CfgObject {
	public final static int TYPEID = -188912940;
	final public int getTypeId() { return TYPEID; }
	public final int year;
	public final int month;
	public final int day;
	public final int hour;
	public final int minute;
	public final int second;
	public DateTime(cfg.DataStream fs) {
		this.year = fs.getInt();
		this.month = fs.getInt();
		this.day = fs.getInt();
		this.hour = fs.getInt();
		this.minute = fs.getInt();
		this.second = fs.getInt();
	}
}
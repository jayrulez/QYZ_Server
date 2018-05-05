package cfg.common;
public final class Timer extends cfg.CfgObject {
	public final static int TYPEID = -1967046996;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final String comment;
	public final int timertype;
	public final int month;
	public final int day;
	public final int hour;
	public final int minute;
	public final int second;
	public Timer(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.comment = fs.getString();
		this.timertype = fs.getInt();
		this.month = fs.getInt();
		this.day = fs.getInt();
		this.hour = fs.getInt();
		this.minute = fs.getInt();
		this.second = fs.getInt();
	}
}
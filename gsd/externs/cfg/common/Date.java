package cfg.common;
public final class Date extends cfg.CfgObject {
	public final static int TYPEID = -1587957913;
	final public int getTypeId() { return TYPEID; }
	public final int year;
	public final int month;
	public final int day;
	public Date(cfg.DataStream fs) {
		this.year = fs.getInt();
		this.month = fs.getInt();
		this.day = fs.getInt();
	}
}
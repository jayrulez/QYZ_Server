package cfg.ectype;
public final class TimeControler extends cfg.CfgObject {
	public final static int TYPEID = -803216623;
	final public int getTypeId() { return TYPEID; }
	public final int hour;
	public final int minute;
	public final int second;
	public TimeControler(cfg.DataStream fs) {
		this.hour = fs.getInt();
		this.minute = fs.getInt();
		this.second = fs.getInt();
	}
}
package cfg.common;
public final class DayTimeRange extends cfg.CfgObject {
	public final static int TYPEID = 1729401069;
	final public int getTypeId() { return TYPEID; }
	public final cfg.common.DayTime begintime;
	public final cfg.common.DayTime endtime;
	public DayTimeRange(cfg.DataStream fs) {
		this.begintime = new cfg.common.DayTime(fs);
		this.endtime = new cfg.common.DayTime(fs);
	}
}
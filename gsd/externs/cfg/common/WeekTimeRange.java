package cfg.common;
public final class WeekTimeRange extends cfg.CfgObject {
	public final static int TYPEID = -1671708349;
	final public int getTypeId() { return TYPEID; }
	public final cfg.common.WeekTime begintime;
	public final cfg.common.WeekTime endtime;
	public WeekTimeRange(cfg.DataStream fs) {
		this.begintime = new cfg.common.WeekTime(fs);
		this.endtime = new cfg.common.WeekTime(fs);
	}
}
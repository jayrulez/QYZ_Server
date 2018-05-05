package cfg.common;
public final class DateTimeRange extends cfg.CfgObject {
	public final static int TYPEID = -913734967;
	final public int getTypeId() { return TYPEID; }
	public final cfg.common.DateTime begintime;
	public final cfg.common.DateTime endtime;
	public DateTimeRange(cfg.DataStream fs) {
		this.begintime = new cfg.common.DateTime(fs);
		this.endtime = new cfg.common.DateTime(fs);
	}
}
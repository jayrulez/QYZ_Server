package cfg.family.citywar;
public final class Broadcast extends cfg.CfgObject {
	public final static int TYPEID = -262657072;
	final public int getTypeId() { return TYPEID; }
	public final cfg.common.WeekTime time;
	public final String content;
	public Broadcast(cfg.DataStream fs) {
		this.time = new cfg.common.WeekTime(fs);
		this.content = fs.getString();
	}
}
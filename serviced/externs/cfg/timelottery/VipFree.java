package cfg.timelottery;
public final class VipFree extends cfg.CfgObject {
	public final static int TYPEID = 1327966061;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final cfg.cmd.condition.DayLimit limit;
	public VipFree(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.limit = new cfg.cmd.condition.DayLimit(fs);
	}
}
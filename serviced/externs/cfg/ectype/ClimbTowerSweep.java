package cfg.ectype;
public final class ClimbTowerSweep extends cfg.CfgObject {
	public final static int TYPEID = -584338036;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.DayLimit limit;
	public ClimbTowerSweep(cfg.DataStream fs) {
		this.limit = new cfg.cmd.condition.DayLimit(fs);
	}
}
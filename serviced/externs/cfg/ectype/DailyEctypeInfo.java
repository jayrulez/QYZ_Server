package cfg.ectype;
public final class DailyEctypeInfo extends cfg.CfgObject {
	public final static int TYPEID = 2142196787;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.VipLimits viptimes;
	public final cfg.ectype.ReviveCost revivecost;
	public final cfg.ectype.ResetOpenCount resetopencountcost;
	public DailyEctypeInfo(cfg.DataStream fs) {
		this.viptimes = new cfg.cmd.condition.VipLimits(fs);
		this.revivecost = new cfg.ectype.ReviveCost(fs);
		this.resetopencountcost = new cfg.ectype.ResetOpenCount(fs);
	}
}
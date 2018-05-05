package cfg.ectype;
public final class ResetOpenCount extends cfg.CfgObject {
	public final static int TYPEID = -892959166;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.VipLimits cost;
	public ResetOpenCount(cfg.DataStream fs) {
		this.cost = new cfg.cmd.condition.VipLimits(fs);
	}
}
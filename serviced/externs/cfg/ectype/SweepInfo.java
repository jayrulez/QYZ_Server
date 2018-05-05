package cfg.ectype;
public final class SweepInfo extends cfg.CfgObject {
	public final static int TYPEID = -603861938;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.Item cost;
	public final cfg.cmd.action.MultiBonus sweepbonus;
	public SweepInfo(cfg.DataStream fs) {
		this.cost = new cfg.cmd.condition.Item(fs);
		this.sweepbonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
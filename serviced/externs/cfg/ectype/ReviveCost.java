package cfg.ectype;
public final class ReviveCost extends cfg.CfgObject {
	public final static int TYPEID = 140485294;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.XuNiBi cost;
	public ReviveCost(cfg.DataStream fs) {
		this.cost = new cfg.cmd.condition.XuNiBi(fs);
	}
}
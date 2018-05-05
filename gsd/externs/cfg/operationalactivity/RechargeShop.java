package cfg.operationalactivity;
public final class RechargeShop extends cfg.operationalactivity.ActivityCondition {
	public final static int TYPEID = 752817812;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.Limit limit;
	public final cfg.cmd.condition.ChongZhiJiFen cost;
	public RechargeShop(cfg.DataStream fs) {
		super(fs);
		this.limit = new cfg.cmd.condition.Limit(fs);
		this.cost = new cfg.cmd.condition.ChongZhiJiFen(fs);
	}
}
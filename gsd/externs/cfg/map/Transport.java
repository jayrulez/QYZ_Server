package cfg.map;
public final class Transport extends cfg.CfgObject {
	public final static int TYPEID = 109070573;
	final public int getTypeId() { return TYPEID; }
	public static final float COLDTIME = 3f;
	public final cfg.cmd.condition.MinLevel minlvl;
	public final cfg.cmd.condition.OneItem requireitemid;
	public final cfg.cmd.condition.VipLimitsLite daylimit;
	public Transport(cfg.DataStream fs) {
		this.minlvl = new cfg.cmd.condition.MinLevel(fs);
		this.requireitemid = new cfg.cmd.condition.OneItem(fs);
		this.daylimit = new cfg.cmd.condition.VipLimitsLite(fs);
	}
}
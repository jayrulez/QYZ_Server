package cfg.ectype;
public final class EctypeSingle extends cfg.CfgObject {
	public final static int TYPEID = -392792020;
	final public int getTypeId() { return TYPEID; }
	public final java.util.Map<Integer, cfg.ectype.DailyEctypeInfo> ectypes = new java.util.HashMap<>();
	public final cfg.cmd.condition.VipLimits resetopencountlimit;
	public final int clientactiontimeout;
	public EctypeSingle(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.ectypes.put(_k, new cfg.ectype.DailyEctypeInfo(fs));
		}
		this.resetopencountlimit = new cfg.cmd.condition.VipLimits(fs);
		this.clientactiontimeout = fs.getInt();
	}
}
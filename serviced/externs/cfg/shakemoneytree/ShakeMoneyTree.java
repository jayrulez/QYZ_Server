package cfg.shakemoneytree;
public final class ShakeMoneyTree extends cfg.CfgObject {
	public final static int TYPEID = -1581951804;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.VipLimitsLite viplimit;
	public final java.util.List<cfg.shakemoneytree.ShakeDetail> shakeinfo = new java.util.ArrayList<>();
	public ShakeMoneyTree(cfg.DataStream fs) {
		this.viplimit = new cfg.cmd.condition.VipLimitsLite(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.shakeinfo.add(new cfg.shakemoneytree.ShakeDetail(fs));
		}
	}
}
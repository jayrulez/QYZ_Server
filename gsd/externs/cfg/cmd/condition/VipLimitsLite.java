package cfg.cmd.condition;
public final class VipLimitsLite extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 121268722;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> entertimes = new java.util.ArrayList<>();
	public VipLimitsLite(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.entertimes.add(fs.getInt());
		}
	}
}
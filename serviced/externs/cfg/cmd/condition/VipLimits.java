package cfg.cmd.condition;
public final class VipLimits extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -1087473884;
	final public int getTypeId() { return TYPEID; }
	public final int currencytype;
	public final java.util.List<Integer> entertimes = new java.util.ArrayList<>();
	public final java.util.List<Integer> amout = new java.util.ArrayList<>();
	public VipLimits(cfg.DataStream fs) {
		super(fs);
		this.currencytype = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.entertimes.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.amout.add(fs.getInt());
		}
	}
}
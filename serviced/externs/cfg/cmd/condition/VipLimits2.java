package cfg.cmd.condition;
public final class VipLimits2 extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 648048014;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> entertimes = new java.util.ArrayList<>();
	public final java.util.List<cfg.cmd.condition.Condition> costs = new java.util.ArrayList<>();
	public VipLimits2(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.entertimes.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.costs.add((cfg.cmd.condition.Condition)fs.getObject(fs.getString()));
		}
	}
}
package cfg.dharmakaya;
public final class BreakThroughCost extends cfg.CfgObject {
	public final static int TYPEID = 1700911088;
	final public int getTypeId() { return TYPEID; }
	public final String name;
	public final java.util.List<cfg.cmd.condition.Condition> cost = new java.util.ArrayList<>();
	public final int giveexp;
	public final cfg.cmd.condition.VipLimitsLite vipfree;
	public final java.util.List<cfg.dharmakaya.BreakThroughCrit> crit = new java.util.ArrayList<>();
	public BreakThroughCost(cfg.DataStream fs) {
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.cost.add((cfg.cmd.condition.Condition)fs.getObject(fs.getString()));
		}
		this.giveexp = fs.getInt();
		this.vipfree = new cfg.cmd.condition.VipLimitsLite(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.crit.add(new cfg.dharmakaya.BreakThroughCrit(fs));
		}
	}
}
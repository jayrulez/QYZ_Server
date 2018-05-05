package cfg.pay;
public final class DayChargeBonusIndex extends cfg.CfgObject {
	public final static int TYPEID = -1498748013;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> index = new java.util.ArrayList<>();
	public DayChargeBonusIndex(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.index.add(fs.getInt());
		}
	}
}
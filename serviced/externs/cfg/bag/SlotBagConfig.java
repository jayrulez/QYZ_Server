package cfg.bag;
public final class SlotBagConfig extends cfg.CfgObject {
	public final static int TYPEID = 2096030332;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.bag.SlotCondition> condtioninfo = new java.util.ArrayList<>();
	public SlotBagConfig(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.condtioninfo.add(new cfg.bag.SlotCondition(fs));
		}
	}
}
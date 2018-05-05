package cfg.equip;
public final class AppendCost extends cfg.CfgObject {
	public final static int TYPEID = -414702865;
	final public int getTypeId() { return TYPEID; }
	public static final int COST_ITEM_ID = 10400006;
	public static final int ACCESSORY_COST_ITEM_ID = 10400037;
	public final int type;
	public final java.util.List<Integer> itemcost = new java.util.ArrayList<>();
	public final java.util.List<Integer> expenses = new java.util.ArrayList<>();
	public AppendCost(cfg.DataStream fs) {
		this.type = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.itemcost.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.expenses.add(fs.getInt());
		}
	}
}
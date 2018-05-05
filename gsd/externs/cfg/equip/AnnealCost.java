package cfg.equip;
public final class AnnealCost extends cfg.CfgObject {
	public final static int TYPEID = -650205404;
	final public int getTypeId() { return TYPEID; }
	public static final int COST_ITEM_ID = 10400001;
	public static final int ACCESSORY_COST_ITEM_ID = 10400036;
	public final int type;
	public final java.util.List<Integer> itemcost = new java.util.ArrayList<>();
	public final java.util.List<Integer> expenses = new java.util.ArrayList<>();
	public AnnealCost(cfg.DataStream fs) {
		this.type = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.itemcost.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.expenses.add(fs.getInt());
		}
	}
}
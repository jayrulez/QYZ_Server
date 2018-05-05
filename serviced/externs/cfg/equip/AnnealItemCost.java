package cfg.equip;
public final class AnnealItemCost extends cfg.CfgObject {
	public final static int TYPEID = -1657908905;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final java.util.List<Integer> itemcost = new java.util.ArrayList<>();
	public AnnealItemCost(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.itemcost.add(fs.getInt());
		}
	}
}
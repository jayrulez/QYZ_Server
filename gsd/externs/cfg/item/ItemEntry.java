package cfg.item;
public final class ItemEntry extends cfg.CfgObject {
	public final static int TYPEID = 278349486;
	final public int getTypeId() { return TYPEID; }
	public final int itemid;
	public final int number;
	public ItemEntry(cfg.DataStream fs) {
		this.itemid = fs.getInt();
		this.number = fs.getInt();
	}
}
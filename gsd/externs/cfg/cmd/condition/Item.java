package cfg.cmd.condition;
public final class Item extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -1233502940;
	final public int getTypeId() { return TYPEID; }
	public final int itemid;
	public final int amount;
	public Item(cfg.DataStream fs) {
		super(fs);
		this.itemid = fs.getInt();
		this.amount = fs.getInt();
	}
}
package cfg.cmd.action;
public final class Item extends cfg.cmd.action.Bonus {
	public final static int TYPEID = -1416272979;
	final public int getTypeId() { return TYPEID; }
	public final int itemid;
	public final int amount;
	public Item(cfg.DataStream fs) {
		super(fs);
		this.itemid = fs.getInt();
		this.amount = fs.getInt();
	}
}
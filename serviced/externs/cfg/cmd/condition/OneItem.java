package cfg.cmd.condition;
public final class OneItem extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 1311421160;
	final public int getTypeId() { return TYPEID; }
	public final int itemid;
	public OneItem(cfg.DataStream fs) {
		super(fs);
		this.itemid = fs.getInt();
	}
}
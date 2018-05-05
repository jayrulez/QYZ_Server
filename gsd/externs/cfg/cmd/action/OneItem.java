package cfg.cmd.action;
public final class OneItem extends cfg.cmd.action.Bonus {
	public final static int TYPEID = -1867246657;
	final public int getTypeId() { return TYPEID; }
	public final int itemid;
	public OneItem(cfg.DataStream fs) {
		super(fs);
		this.itemid = fs.getInt();
	}
}
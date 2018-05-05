package cfg.cmd.action;
public final class Currency extends cfg.cmd.action.Bonus {
	public final static int TYPEID = 838204331;
	final public int getTypeId() { return TYPEID; }
	public final int type;
	public final int amount;
	public Currency(cfg.DataStream fs) {
		super(fs);
		this.type = fs.getInt();
		this.amount = fs.getInt();
	}
}
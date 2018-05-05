package cfg.cmd.condition;
public final class Currency extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 592658850;
	final public int getTypeId() { return TYPEID; }
	public final int currencytype;
	public final int amount;
	public Currency(cfg.DataStream fs) {
		super(fs);
		this.currencytype = fs.getInt();
		this.amount = fs.getInt();
	}
}
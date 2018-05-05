package cfg.cmd.condition;
public final class FamilyMoney extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 1982549131;
	final public int getTypeId() { return TYPEID; }
	public final long money;
	public FamilyMoney(cfg.DataStream fs) {
		super(fs);
		this.money = fs.getLong();
	}
}
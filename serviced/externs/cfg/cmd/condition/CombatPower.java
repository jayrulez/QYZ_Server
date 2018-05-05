package cfg.cmd.condition;
public final class CombatPower extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -300496064;
	final public int getTypeId() { return TYPEID; }
	public final long amount;
	public CombatPower(cfg.DataStream fs) {
		super(fs);
		this.amount = fs.getLong();
	}
}
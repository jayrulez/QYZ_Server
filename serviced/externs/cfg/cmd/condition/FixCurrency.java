package cfg.cmd.condition;
public abstract class FixCurrency extends cfg.cmd.condition.Condition {
	public final int amount;
	public FixCurrency(cfg.DataStream fs) {
		super(fs);
		this.amount = fs.getInt();
	}
}
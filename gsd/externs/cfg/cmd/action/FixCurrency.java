package cfg.cmd.action;
public abstract class FixCurrency extends cfg.cmd.action.Bonus {
	public final int amount;
	public FixCurrency(cfg.DataStream fs) {
		super(fs);
		this.amount = fs.getInt();
	}
}
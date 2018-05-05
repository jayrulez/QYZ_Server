package cfg.cmd.action;
public abstract class AddExperience extends cfg.cmd.action.Action {
	public final long amount;
	public AddExperience(cfg.DataStream fs) {
		super(fs);
		this.amount = fs.getLong();
	}
}
package cfg.cmd.condition;
public final class Gender extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -56200046;
	final public int getTypeId() { return TYPEID; }
	public final int gender;
	public Gender(cfg.DataStream fs) {
		super(fs);
		this.gender = fs.getInt();
	}
}
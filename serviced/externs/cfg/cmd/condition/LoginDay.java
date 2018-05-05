package cfg.cmd.condition;
public final class LoginDay extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 2039990532;
	final public int getTypeId() { return TYPEID; }
	public final int day;
	public LoginDay(cfg.DataStream fs) {
		super(fs);
		this.day = fs.getInt();
	}
}
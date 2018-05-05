package cfg.cmd.condition;
public final class DayLimit extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 1930693392;
	final public int getTypeId() { return TYPEID; }
	public final int num;
	public DayLimit(cfg.DataStream fs) {
		super(fs);
		this.num = fs.getInt();
	}
}
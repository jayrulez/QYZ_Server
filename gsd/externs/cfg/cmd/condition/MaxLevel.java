package cfg.cmd.condition;
public final class MaxLevel extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 407377425;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public MaxLevel(cfg.DataStream fs) {
		super(fs);
		this.level = fs.getInt();
	}
}
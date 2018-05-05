package cfg.cmd.condition;
public final class MinLevel extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -1368819229;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public MinLevel(cfg.DataStream fs) {
		super(fs);
		this.level = fs.getInt();
	}
}
package cfg.cmd.condition;
public final class MinMaxLevel extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 447698369;
	final public int getTypeId() { return TYPEID; }
	public final int min;
	public final int max;
	public MinMaxLevel(cfg.DataStream fs) {
		super(fs);
		this.min = fs.getInt();
		this.max = fs.getInt();
	}
}
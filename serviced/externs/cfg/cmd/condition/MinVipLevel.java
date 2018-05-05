package cfg.cmd.condition;
public final class MinVipLevel extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 1529151144;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public MinVipLevel(cfg.DataStream fs) {
		super(fs);
		this.level = fs.getInt();
	}
}
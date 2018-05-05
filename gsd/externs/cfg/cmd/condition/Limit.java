package cfg.cmd.condition;
public final class Limit extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 418565066;
	final public int getTypeId() { return TYPEID; }
	public final int type;
	public final int num;
	public Limit(cfg.DataStream fs) {
		super(fs);
		this.type = fs.getInt();
		this.num = fs.getInt();
	}
}
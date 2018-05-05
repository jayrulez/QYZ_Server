package cfg.cmd.condition;
public final class OR extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 1728323604;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.Condition first;
	public final cfg.cmd.condition.Condition second;
	public OR(cfg.DataStream fs) {
		super(fs);
		this.first = (cfg.cmd.condition.Condition)fs.getObject(fs.getString());
		this.second = (cfg.cmd.condition.Condition)fs.getObject(fs.getString());
	}
}
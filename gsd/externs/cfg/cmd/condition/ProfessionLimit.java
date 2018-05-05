package cfg.cmd.condition;
public final class ProfessionLimit extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -261443218;
	final public int getTypeId() { return TYPEID; }
	public final int profession;
	public ProfessionLimit(cfg.DataStream fs) {
		super(fs);
		this.profession = fs.getInt();
	}
}
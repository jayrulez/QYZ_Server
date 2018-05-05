package cfg.cmd.condition;
public final class TeamMemberNumber extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 1654757905;
	final public int getTypeId() { return TYPEID; }
	public final int min;
	public final int max;
	public TeamMemberNumber(cfg.DataStream fs) {
		super(fs);
		this.min = fs.getInt();
		this.max = fs.getInt();
	}
}
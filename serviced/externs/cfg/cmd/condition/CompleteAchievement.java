package cfg.cmd.condition;
public final class CompleteAchievement extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 787411461;
	final public int getTypeId() { return TYPEID; }
	public final int achievementid;
	public CompleteAchievement(cfg.DataStream fs) {
		super(fs);
		this.achievementid = fs.getInt();
	}
}
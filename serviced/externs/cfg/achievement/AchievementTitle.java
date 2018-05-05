package cfg.achievement;
public final class AchievementTitle extends cfg.achievement.Achievement {
	public final static int TYPEID = -1093640526;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.OneItem titleid;
	public AchievementTitle(cfg.DataStream fs) {
		super(fs);
		this.titleid = new cfg.cmd.action.OneItem(fs);
	}
}
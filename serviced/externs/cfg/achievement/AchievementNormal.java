package cfg.achievement;
public final class AchievementNormal extends cfg.achievement.Achievement {
	public final static int TYPEID = 290589645;
	final public int getTypeId() { return TYPEID; }
	public final String icon;
	public final cfg.cmd.action.ChengJiu chengjiupoint;
	public final cfg.cmd.action.BindYuanBao bindyuanbao;
	public AchievementNormal(cfg.DataStream fs) {
		super(fs);
		this.icon = fs.getString();
		this.chengjiupoint = new cfg.cmd.action.ChengJiu(fs);
		this.bindyuanbao = new cfg.cmd.action.BindYuanBao(fs);
	}
}
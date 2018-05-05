package cfg.ectype;
public final class TeamStoryEctype extends cfg.CfgObject {
	public final static int TYPEID = -771110108;
	final public int getTypeId() { return TYPEID; }
	public static final int MATCH_TIME_MAX = 60;
	public static final int MATCH_SUCCESS_WAITING_TIME = 5;
	public static final int TEAM_NUM = 4;
	public final int id;
	public final String bgmpic;
	public final String ectypepic;
	public final String storyname;
	public final String introduction;
	public final int difficulty;
	public final java.util.List<Integer> showbonusid = new java.util.ArrayList<>();
	public final int preectypeid;
	public final int groupid;
	public final int sectionid;
	public final cfg.cmd.condition.TiLi costtili;
	public final cfg.cmd.condition.MinLevel openlevel;
	public final cfg.cmd.condition.CompleteTask tasklimit;
	public final int battlepower;
	public final java.util.List<cfg.ectype.StarConditionInfo> starcondition = new java.util.ArrayList<>();
	public final cfg.cmd.action.MultiBonus starbonus;
	public final cfg.cmd.action.MultiBonus ectypedrop;
	public final java.util.List<Integer> showaward = new java.util.ArrayList<>();
	public final int timelimit;
	public final cfg.cmd.condition.DayLimit daylimit;
	public TeamStoryEctype(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.bgmpic = fs.getString();
		this.ectypepic = fs.getString();
		this.storyname = fs.getString();
		this.introduction = fs.getString();
		this.difficulty = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.showbonusid.add(fs.getInt());
		}
		this.preectypeid = fs.getInt();
		this.groupid = fs.getInt();
		this.sectionid = fs.getInt();
		this.costtili = new cfg.cmd.condition.TiLi(fs);
		this.openlevel = new cfg.cmd.condition.MinLevel(fs);
		this.tasklimit = new cfg.cmd.condition.CompleteTask(fs);
		this.battlepower = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.starcondition.add(new cfg.ectype.StarConditionInfo(fs));
		}
		this.starbonus = new cfg.cmd.action.MultiBonus(fs);
		this.ectypedrop = new cfg.cmd.action.MultiBonus(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.showaward.add(fs.getInt());
		}
		this.timelimit = fs.getInt();
		this.daylimit = new cfg.cmd.condition.DayLimit(fs);
	}
}
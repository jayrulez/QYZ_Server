package cfg.ectype;
public final class PersonalBoss extends cfg.CfgObject {
	public final static int TYPEID = -1861901639;
	final public int getTypeId() { return TYPEID; }
	public static final int TALK_INTERVAL = 5;
	public static final int TALK_LAST = 5;
	public static final int ENDOFFTIME = 1;
	public static final int REVIVETIME = 30;
	public final int ectypeid;
	public final int battlepower;
	public final cfg.cmd.condition.MinLevel openlevel;
	public final cfg.cmd.condition.MinVipLevel viplimit;
	public final cfg.cmd.condition.CompleteTask tasklimit;
	public final String taskdescribe;
	public final String name;
	public final java.util.List<Integer> showbonusitemid = new java.util.ArrayList<>();
	public final int bossid;
	public final String allowicon;
	public final String forbidicon;
	public final String bossicon;
	public final float localposx;
	public final float localposy;
	public final float scale;
	public final String bosstalk;
	public final String introduction;
	public final int mainregionid;
	public final int monsterreftime;
	public final java.util.List<cfg.ectype.MonsterInfo> monsters = new java.util.ArrayList<>();
	public final cfg.cmd.action.Bonus bonus;
	public final cfg.cmd.condition.VipLimits daylimit;
	public PersonalBoss(cfg.DataStream fs) {
		this.ectypeid = fs.getInt();
		this.battlepower = fs.getInt();
		this.openlevel = new cfg.cmd.condition.MinLevel(fs);
		this.viplimit = new cfg.cmd.condition.MinVipLevel(fs);
		this.tasklimit = new cfg.cmd.condition.CompleteTask(fs);
		this.taskdescribe = fs.getString();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.showbonusitemid.add(fs.getInt());
		}
		this.bossid = fs.getInt();
		this.allowicon = fs.getString();
		this.forbidicon = fs.getString();
		this.bossicon = fs.getString();
		this.localposx = fs.getFloat();
		this.localposy = fs.getFloat();
		this.scale = fs.getFloat();
		this.bosstalk = fs.getString();
		this.introduction = fs.getString();
		this.mainregionid = fs.getInt();
		this.monsterreftime = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsters.add(new cfg.ectype.MonsterInfo(fs));
		}
		this.bonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
		this.daylimit = new cfg.cmd.condition.VipLimits(fs);
	}
}
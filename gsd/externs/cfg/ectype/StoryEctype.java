package cfg.ectype;
public final class StoryEctype extends cfg.CfgObject {
	public final static int TYPEID = -340821055;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String storyname;
	public final String introduction;
	public final int chapter;
	public final int section;
	public final cfg.cmd.condition.TiLi costtili;
	public final cfg.cmd.condition.MinLevel openlevel;
	public final int battlepower;
	public final int sweepcondition;
	public final java.util.List<cfg.ectype.StarConditionInfo> starcondition = new java.util.ArrayList<>();
	public final cfg.cmd.action.MultiBonus starbonus;
	public final cfg.cmd.action.MultiBonus ectypedrop;
	public final cfg.cmd.condition.DayLimit daylimit;
	public final String bgmpic;
	public final boolean ifend;
	public final boolean storybossicon;
	public StoryEctype(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.storyname = fs.getString();
		this.introduction = fs.getString();
		this.chapter = fs.getInt();
		this.section = fs.getInt();
		this.costtili = new cfg.cmd.condition.TiLi(fs);
		this.openlevel = new cfg.cmd.condition.MinLevel(fs);
		this.battlepower = fs.getInt();
		this.sweepcondition = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.starcondition.add(new cfg.ectype.StarConditionInfo(fs));
		}
		this.starbonus = new cfg.cmd.action.MultiBonus(fs);
		this.ectypedrop = new cfg.cmd.action.MultiBonus(fs);
		this.daylimit = new cfg.cmd.condition.DayLimit(fs);
		this.bgmpic = fs.getString();
		this.ifend = fs.getBool();
		this.storybossicon = fs.getBool();
	}
}
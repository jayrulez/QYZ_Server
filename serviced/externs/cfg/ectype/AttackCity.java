package cfg.ectype;
public final class AttackCity extends cfg.CfgObject {
	public final static int TYPEID = -394932289;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String bgpic;
	public final String introduce;
	public final cfg.cmd.condition.MinLevel requirelevel;
	public final cfg.common.WeekTimeRange signuptime;
	public final cfg.common.WeekTimeRange opentime;
	public final java.util.List<cfg.ectype.AttackCityLevelSection> sections = new java.util.ArrayList<>();
	public AttackCity(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.bgpic = fs.getString();
		this.introduce = fs.getString();
		this.requirelevel = new cfg.cmd.condition.MinLevel(fs);
		this.signuptime = new cfg.common.WeekTimeRange(fs);
		this.opentime = new cfg.common.WeekTimeRange(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.sections.add(new cfg.ectype.AttackCityLevelSection(fs));
		}
	}
}
package cfg.ectype;
public final class MMEctype extends cfg.CfgObject {
	public final static int TYPEID = 653539684;
	final public int getTypeId() { return TYPEID; }
	public static final int MM_ID = 60490501;
	public static final int SPRING_ID = 60490502;
	public final int ectypeid;
	public final cfg.cmd.condition.DayLimit dailyrewardtime;
	public final int minteamnum;
	public final int openlevel;
	public final String decs;
	public final cfg.cmd.action.OneItems showrewards;
	public final java.util.List<cfg.ectype.MMLevelMonserInfo> levelmonsterinfos = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.MMLevelReward> levelbonus = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.MMLevelReward> levelbonus_level= new java.util.HashMap<>();
	public MMEctype(cfg.DataStream fs) {
		this.ectypeid = fs.getInt();
		this.dailyrewardtime = new cfg.cmd.condition.DayLimit(fs);
		this.minteamnum = fs.getInt();
		this.openlevel = fs.getInt();
		this.decs = fs.getString();
		this.showrewards = new cfg.cmd.action.OneItems(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.levelmonsterinfos.add(new cfg.ectype.MMLevelMonserInfo(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.MMLevelReward _v = new cfg.ectype.MMLevelReward(fs);
			this.levelbonus.add(_v);
			this.levelbonus_level.put(_v.level, _v);
		}
	}
}
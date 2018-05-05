package cfg.arena;
public final class ArenaConfig extends cfg.CfgObject {
	public final static int TYPEID = -1114721896;
	final public int getTypeId() { return TYPEID; }
	public static final String firsttitle = "天下第一";
	public static final String secondtitle = "天下第二";
	public static final String thirdtitle = "天下第三";
	public final int openlevel;
	public final int shengwangrefreshinterval;
	public final int maxreportnum;
	public final String arrowindex;
	public final float arrowdistance;
	public final cfg.cmd.condition.VipLimits challengelimit;
	public final cfg.cmd.condition.VipLimits refreshopponentlimit;
	public final java.util.List<cfg.bonus.RankBonusList> rankbonus = new java.util.ArrayList<>();
	public final java.util.List<cfg.arena.ArenaAward> awardlist = new java.util.ArrayList<>();
	public final java.util.List<cfg.arena.ArenaShengwang> shengwangsteplist = new java.util.ArrayList<>();
	public final java.util.List<cfg.arena.ArenaSpecialAward> specialawardlist = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.arena.ArenaSpecialAward> specialawardlist_times= new java.util.HashMap<>();
	public final java.util.List<cfg.arena.ArenaRankStep> ranksteps = new java.util.ArrayList<>();
	public final String introduction;
	public ArenaConfig(cfg.DataStream fs) {
		this.openlevel = fs.getInt();
		this.shengwangrefreshinterval = fs.getInt();
		this.maxreportnum = fs.getInt();
		this.arrowindex = fs.getString();
		this.arrowdistance = fs.getFloat();
		this.challengelimit = new cfg.cmd.condition.VipLimits(fs);
		this.refreshopponentlimit = new cfg.cmd.condition.VipLimits(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.rankbonus.add(new cfg.bonus.RankBonusList(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.awardlist.add(new cfg.arena.ArenaAward(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.shengwangsteplist.add(new cfg.arena.ArenaShengwang(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.arena.ArenaSpecialAward _v = new cfg.arena.ArenaSpecialAward(fs);
			this.specialawardlist.add(_v);
			this.specialawardlist_times.put(_v.times, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.ranksteps.add(new cfg.arena.ArenaRankStep(fs));
		}
		this.introduction = fs.getString();
	}
}
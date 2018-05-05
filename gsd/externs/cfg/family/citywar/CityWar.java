package cfg.family.citywar;
public final class CityWar extends cfg.CfgObject {
	public final static int TYPEID = 791312460;
	final public int getTypeId() { return TYPEID; }
	public static final int MAX_ALLOC_LOG_NUM = 20;
	public static final int MAX_BATTLE_LOG_NUM = 50;
	public static final int LUCKY_BONUS_ALLOC_MAILID = 21;
	public static final int DECLARE_FAIL_MAILID = 22;
	public static final int DAILY_BNOUS_MAILID = 23;
	public static final int BATTLE_SUCC_ATTACK_MAILID = 24;
	public static final int BATTLE_SUCC_DEFENCE_MAILID = 25;
	public static final int BATTLE_FAIL_ATTACK_MAILID = 26;
	public static final int BATTLE_FAIL_DEFENCE_MAILID = 27;
	public static final int DEFAULT_COLOR_R = 149;
	public static final int DEFAULT_COLOR_G = 149;
	public static final int DEFAULT_COLOR_B = 149;
	public static final int LUCKY_BONUS_NEED_MIN_JOIN_FAMILY_TIME = 604800;
	public final int ectypeid;
	public final int bornregion1;
	public final int bornregion2;
	public final int reviveregion1;
	public final int reviveregion2;
	public final int regionbeforebattlebegin1;
	public final int regionbeforebattlebegin2;
	public final int mainregionid;
	public final int maxbattlefamilymembernum;
	public final int revivebuffid;
	public final int countdown;
	public final int stabilitybuffid;
	public final int winscore;
	public final int addcapturetowerscore;
	public final int addcapturetowerscoreinterval;
	public final int scoremultiratewhilecaptureall;
	public final int maxbattlenum;
	public final cfg.cmd.condition.MinFamilyLevel minfamilylevel;
	public final int occupytime;
	public final float bornorient1;
	public final float bornorient2;
	public final java.util.List<cfg.family.citywar.LevelMonsters> levelbosses = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.family.citywar.LevelMonsters> levelbosses_level= new java.util.HashMap<>();
	public final java.util.List<cfg.family.citywar.LevelMonsters> levelmonsters = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.family.citywar.LevelMonsters> levelmonsters_level= new java.util.HashMap<>();
	public final java.util.List<cfg.family.citywar.CityWarTower> towers = new java.util.ArrayList<>();
	public final int towerradius;
	public final java.util.Map<Integer, String> occupymodel = new java.util.HashMap<>();
	public final cfg.common.WeekTimeRange enrolltime;
	public final java.util.List<cfg.common.WeekTimeRange> battletimes = new java.util.ArrayList<>();
	public final cfg.common.WeekTime luckybonustime;
	public final java.util.List<cfg.family.citywar.CityLevelInfo> citylevels = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.family.citywar.CityLevelInfo> citylevels_level= new java.util.HashMap<>();
	public final java.util.List<cfg.family.citywar.ScoreBonus> scorebonuss = new java.util.ArrayList<>();
	public final cfg.cmd.action.Bonus luckybonus;
	public final int luckybonusnum;
	public final float attackerwinmoneyrate;
	public final float defencerwinmoneyrate;
	public final java.util.List<cfg.family.citywar.CityInfo> citys = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.family.citywar.CityInfo> citys_cityid= new java.util.HashMap<>();
	public final java.util.List<String> colors = new java.util.ArrayList<>();
	public final java.util.List<cfg.family.citywar.CitywarTip> tips = new java.util.ArrayList<>();
	public final String maintips;
	public final String tipsworld;
	public final String tipsdeclare;
	public final String tipsmine;
	public final String tipsaward;
	public final java.util.List<cfg.family.citywar.Broadcast> broadcasts = new java.util.ArrayList<>();
	public final String luckbonusbroadcast;
	public final String battledefencesuccbroadcast;
	public final String battledefencefailbroadcast;
	public final String battlecaptureemptycitybroadcast;
	public CityWar(cfg.DataStream fs) {
		this.ectypeid = fs.getInt();
		this.bornregion1 = fs.getInt();
		this.bornregion2 = fs.getInt();
		this.reviveregion1 = fs.getInt();
		this.reviveregion2 = fs.getInt();
		this.regionbeforebattlebegin1 = fs.getInt();
		this.regionbeforebattlebegin2 = fs.getInt();
		this.mainregionid = fs.getInt();
		this.maxbattlefamilymembernum = fs.getInt();
		this.revivebuffid = fs.getInt();
		this.countdown = fs.getInt();
		this.stabilitybuffid = fs.getInt();
		this.winscore = fs.getInt();
		this.addcapturetowerscore = fs.getInt();
		this.addcapturetowerscoreinterval = fs.getInt();
		this.scoremultiratewhilecaptureall = fs.getInt();
		this.maxbattlenum = fs.getInt();
		this.minfamilylevel = new cfg.cmd.condition.MinFamilyLevel(fs);
		this.occupytime = fs.getInt();
		this.bornorient1 = fs.getFloat();
		this.bornorient2 = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.family.citywar.LevelMonsters _v = new cfg.family.citywar.LevelMonsters(fs);
			this.levelbosses.add(_v);
			this.levelbosses_level.put(_v.level, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.family.citywar.LevelMonsters _v = new cfg.family.citywar.LevelMonsters(fs);
			this.levelmonsters.add(_v);
			this.levelmonsters_level.put(_v.level, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.towers.add(new cfg.family.citywar.CityWarTower(fs));
		}
		this.towerradius = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.occupymodel.put(_k, fs.getString());
		}
		this.enrolltime = new cfg.common.WeekTimeRange(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.battletimes.add(new cfg.common.WeekTimeRange(fs));
		}
		this.luckybonustime = new cfg.common.WeekTime(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.family.citywar.CityLevelInfo _v = new cfg.family.citywar.CityLevelInfo(fs);
			this.citylevels.add(_v);
			this.citylevels_level.put(_v.level, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.scorebonuss.add(new cfg.family.citywar.ScoreBonus(fs));
		}
		this.luckybonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
		this.luckybonusnum = fs.getInt();
		this.attackerwinmoneyrate = fs.getFloat();
		this.defencerwinmoneyrate = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.family.citywar.CityInfo _v = new cfg.family.citywar.CityInfo(fs);
			this.citys.add(_v);
			this.citys_cityid.put(_v.cityid, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.colors.add(fs.getString());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.tips.add(new cfg.family.citywar.CitywarTip(fs));
		}
		this.maintips = fs.getString();
		this.tipsworld = fs.getString();
		this.tipsdeclare = fs.getString();
		this.tipsmine = fs.getString();
		this.tipsaward = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.broadcasts.add(new cfg.family.citywar.Broadcast(fs));
		}
		this.luckbonusbroadcast = fs.getString();
		this.battledefencesuccbroadcast = fs.getString();
		this.battledefencefailbroadcast = fs.getString();
		this.battlecaptureemptycitybroadcast = fs.getString();
	}
}
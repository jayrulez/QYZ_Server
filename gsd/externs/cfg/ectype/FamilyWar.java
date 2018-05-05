package cfg.ectype;
public final class FamilyWar extends cfg.CfgObject {
	public final static int TYPEID = 353412984;
	final public int getTypeId() { return TYPEID; }
	public static final int WAR_WIN_MAIL_ID = 18;
	public static final int WAR_FAIL_MAIL_ID = 19;
	public static final int WAR_DRAW_MAIL_ID = 20;
	public final int ectypeid;
	public final int bornregion1;
	public final int bornregion2;
	public final int reviveregion1;
	public final int reviveregion2;
	public final int regionbeforebattlebegin1;
	public final int regionbeforebattlebegin2;
	public final int mainregionid;
	public final int reentercooldown;
	public final int daychallengenum;
	public final int minfamilylevel;
	public final int minonlinemembernum;
	public final int challengeexpiretime;
	public final int battlenum;
	public final int maxfamilymembernum;
	public final int maxhistorynum;
	public final int revivebuffid;
	public final int countdown;
	public final java.util.List<cfg.ectype.FamilyWarLevelRangeInfo> levelranges = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.FamilyWarLevelRangeInfo> levelranges_minlevel= new java.util.HashMap<>();
	public final java.util.List<cfg.ectype.BossMonsterInfo> bossranges = new java.util.ArrayList<>();
	public final int killplayerorpetscore;
	public final int killanimalscore;
	public final java.util.List<Integer> killtowerscores = new java.util.ArrayList<>();
	public final java.util.List<cfg.map.Vector2> towerpositions1 = new java.util.ArrayList<>();
	public final java.util.List<cfg.map.Vector2> towerpositions2 = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.FamilyWarAward> victoryaward = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.FamilyWarAward> defeataward = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.FamilyWarAward> drawaward = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.cmd.action.MultiBonus> damagerankingaward = new java.util.HashMap<>();
	public final int winfamilybonus;
	public final int losefamilybonus;
	public final java.util.List<cfg.common.WeekTimeRange> timelimit = new java.util.ArrayList<>();
	public final String decs;
	public FamilyWar(cfg.DataStream fs) {
		this.ectypeid = fs.getInt();
		this.bornregion1 = fs.getInt();
		this.bornregion2 = fs.getInt();
		this.reviveregion1 = fs.getInt();
		this.reviveregion2 = fs.getInt();
		this.regionbeforebattlebegin1 = fs.getInt();
		this.regionbeforebattlebegin2 = fs.getInt();
		this.mainregionid = fs.getInt();
		this.reentercooldown = fs.getInt();
		this.daychallengenum = fs.getInt();
		this.minfamilylevel = fs.getInt();
		this.minonlinemembernum = fs.getInt();
		this.challengeexpiretime = fs.getInt();
		this.battlenum = fs.getInt();
		this.maxfamilymembernum = fs.getInt();
		this.maxhistorynum = fs.getInt();
		this.revivebuffid = fs.getInt();
		this.countdown = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.FamilyWarLevelRangeInfo _v = new cfg.ectype.FamilyWarLevelRangeInfo(fs);
			this.levelranges.add(_v);
			this.levelranges_minlevel.put(_v.minlevel, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bossranges.add(new cfg.ectype.BossMonsterInfo(fs));
		}
		this.killplayerorpetscore = fs.getInt();
		this.killanimalscore = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.killtowerscores.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.towerpositions1.add(new cfg.map.Vector2(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.towerpositions2.add(new cfg.map.Vector2(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.victoryaward.add(new cfg.ectype.FamilyWarAward(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.defeataward.add(new cfg.ectype.FamilyWarAward(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.drawaward.add(new cfg.ectype.FamilyWarAward(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.damagerankingaward.put(_k, new cfg.cmd.action.MultiBonus(fs));
		}
		this.winfamilybonus = fs.getInt();
		this.losefamilybonus = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.timelimit.add(new cfg.common.WeekTimeRange(fs));
		}
		this.decs = fs.getString();
	}
}
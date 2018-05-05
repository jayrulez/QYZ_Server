package cfg.ectype;
public final class FamilyLeague extends cfg.CfgObject {
	public final static int TYPEID = 1249733535;
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
	public final int minfamilylevel;
	public final int battlenum;
	public final int maxfamilymembernum;
	public final int revivebuffid;
	public final int countdown;
	public final int groupnum;
	public final int winscore;
	public final int drawscore;
	public final int failscore;
	public final java.util.List<cfg.ectype.FamilyLeagueLevelRangeInfo> levelranges = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.FamilyLeagueLevelRangeInfo> levelranges_minlevel= new java.util.HashMap<>();
	public final java.util.List<cfg.ectype.BossMonsterInfo> bossranges = new java.util.ArrayList<>();
	public final int killplayerorpetscore;
	public final int killanimalscore;
	public final java.util.List<Integer> killtowerscores = new java.util.ArrayList<>();
	public final java.util.List<cfg.map.Vector2> towerpositions1 = new java.util.ArrayList<>();
	public final java.util.List<cfg.map.Vector2> towerpositions2 = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.FamilyLeagueAward1> victoryaward = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.FamilyLeagueAward1> defeataward = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.FamilyLeagueAward2> familyrankingaward = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.FamilyLeaguemvps> leaguemvps = new java.util.ArrayList<>();
	public final java.util.List<cfg.common.WeekTimeRange> battletime = new java.util.ArrayList<>();
	public final String decs1;
	public final String decs2;
	public final String decs3;
	public final String decs4;
	public final String SEASON_BONUS_MAIL_TITLE;
	public final String SEASON_BONUS_MAIL_CONTENT;
	public final String ROUND_EMPTY_MAIL_TITLE;
	public FamilyLeague(cfg.DataStream fs) {
		this.ectypeid = fs.getInt();
		this.bornregion1 = fs.getInt();
		this.bornregion2 = fs.getInt();
		this.reviveregion1 = fs.getInt();
		this.reviveregion2 = fs.getInt();
		this.regionbeforebattlebegin1 = fs.getInt();
		this.regionbeforebattlebegin2 = fs.getInt();
		this.mainregionid = fs.getInt();
		this.reentercooldown = fs.getInt();
		this.minfamilylevel = fs.getInt();
		this.battlenum = fs.getInt();
		this.maxfamilymembernum = fs.getInt();
		this.revivebuffid = fs.getInt();
		this.countdown = fs.getInt();
		this.groupnum = fs.getInt();
		this.winscore = fs.getInt();
		this.drawscore = fs.getInt();
		this.failscore = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.FamilyLeagueLevelRangeInfo _v = new cfg.ectype.FamilyLeagueLevelRangeInfo(fs);
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
			this.victoryaward.add(new cfg.ectype.FamilyLeagueAward1(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.defeataward.add(new cfg.ectype.FamilyLeagueAward1(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.familyrankingaward.add(new cfg.ectype.FamilyLeagueAward2(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.leaguemvps.add(new cfg.ectype.FamilyLeaguemvps(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.battletime.add(new cfg.common.WeekTimeRange(fs));
		}
		this.decs1 = fs.getString();
		this.decs2 = fs.getString();
		this.decs3 = fs.getString();
		this.decs4 = fs.getString();
		this.SEASON_BONUS_MAIL_TITLE = fs.getString();
		this.SEASON_BONUS_MAIL_CONTENT = fs.getString();
		this.ROUND_EMPTY_MAIL_TITLE = fs.getString();
	}
}
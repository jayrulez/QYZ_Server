package cfg.huiwu;
public final class HuiWu extends cfg.CfgObject {
	public final static int TYPEID = -755509188;
	final public int getTypeId() { return TYPEID; }
	public static final int ELIMINATE_RANK = 3088;
	public static final int BAOSONG_RANK = 12;
	public static final int DIRECT_ELECTION = 4;
	public static final int PRESELECT_BROCAST_TERMINAL = 4;
	public static final int HUIWU_PRESELECTION1_SUCC_MAILID = 4;
	public static final int HUIWU_PRESELECTION1_FAIL_MAILID = 5;
	public static final int HUIWU_PRESELECTION2_SUCC_MAILID = 6;
	public static final int HUIWU_PRESELECTION2_FAIL_MAILID = 7;
	public static final int HUIWU_BATTLE_FAIL_MAILID = 8;
	public static final int HUIWU_BATTLE_WIN_MAILID = 14;
	public static final int HUIWU_CHAMPION_MAILID = 9;
	public static final int HUIWU_CONTINUE_CHAMPION_MAILID = 10;
	public static final int CHAMPION_CTX_CELEBRITY = 1;
	public static final int CHAMPION_CTX_MAIN = 2;
	public final int ectypeid;
	public final cfg.cmd.condition.MinLevel requirelevel;
	public final int areaid;
	public final cfg.map.Vector2 player1bornposition;
	public final cfg.map.Vector2 player2bornposition;
	public final int countdown;
	public final int ectypetime;
	public final int preselect1num;
	public final int preselect2num;
	public final int preselect2priornum;
	public final int preselect2luckynum;
	public final java.util.List<Integer> specialranks = new java.util.ArrayList<>();
	public final java.util.List<Integer> specialpositions = new java.util.ArrayList<>();
	public final cfg.common.WeekTime enrollopen;
	public final cfg.common.WeekTime enrollend;
	public final cfg.common.WeekTime preselectopen1;
	public final cfg.common.WeekTime preselectend1;
	public final cfg.common.WeekTime preselectopen2;
	public final cfg.common.WeekTime preselectend2;
	public final cfg.common.WeekTime battleopen;
	public final int mainregionid;
	public final java.util.List<cfg.huiwu.BattleTimeControler> battletime = new java.util.ArrayList<>();
	public final cfg.cmd.condition.DayLimit daiylyworshiptimes;
	public final cfg.cmd.action.MultiBonus dailyworshipaward;
	public final cfg.cmd.action.MultiBonus guessaward;
	public final cfg.cmd.action.MultiBonus enrollaward;
	public final cfg.cmd.action.MultiBonus preselectaward;
	public final java.util.List<cfg.huiwu.rankAward> battleaward = new java.util.ArrayList<>();
	public final int continuitytimes;
	public final cfg.cmd.action.MultiBonus continuityaward;
	public HuiWu(cfg.DataStream fs) {
		this.ectypeid = fs.getInt();
		this.requirelevel = new cfg.cmd.condition.MinLevel(fs);
		this.areaid = fs.getInt();
		this.player1bornposition = new cfg.map.Vector2(fs);
		this.player2bornposition = new cfg.map.Vector2(fs);
		this.countdown = fs.getInt();
		this.ectypetime = fs.getInt();
		this.preselect1num = fs.getInt();
		this.preselect2num = fs.getInt();
		this.preselect2priornum = fs.getInt();
		this.preselect2luckynum = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.specialranks.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.specialpositions.add(fs.getInt());
		}
		this.enrollopen = new cfg.common.WeekTime(fs);
		this.enrollend = new cfg.common.WeekTime(fs);
		this.preselectopen1 = new cfg.common.WeekTime(fs);
		this.preselectend1 = new cfg.common.WeekTime(fs);
		this.preselectopen2 = new cfg.common.WeekTime(fs);
		this.preselectend2 = new cfg.common.WeekTime(fs);
		this.battleopen = new cfg.common.WeekTime(fs);
		this.mainregionid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.battletime.add(new cfg.huiwu.BattleTimeControler(fs));
		}
		this.daiylyworshiptimes = new cfg.cmd.condition.DayLimit(fs);
		this.dailyworshipaward = new cfg.cmd.action.MultiBonus(fs);
		this.guessaward = new cfg.cmd.action.MultiBonus(fs);
		this.enrollaward = new cfg.cmd.action.MultiBonus(fs);
		this.preselectaward = new cfg.cmd.action.MultiBonus(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.battleaward.add(new cfg.huiwu.rankAward(fs));
		}
		this.continuitytimes = fs.getInt();
		this.continuityaward = new cfg.cmd.action.MultiBonus(fs);
	}
}
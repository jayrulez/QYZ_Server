package cfg.ectype;
public final class TeamFight extends cfg.CfgObject {
	public final static int TYPEID = 1513980487;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int mainregionid;
	public final int airwallregionid;
	public final int team1bornregionid;
	public final int team2bornregionid;
	public final int levellimit;
	public final int matchnum;
	public final int quitmatchforbidtime;
	public final int petrevivetime;
	public final cfg.ectype.BroadcastInfo broadcastinfo;
	public final java.util.List<cfg.common.DayTimeRange> opentimes = new java.util.ArrayList<>();
	public final cfg.cmd.condition.DayLimit extrabonustimes;
	public final int dailywintimes;
	public final cfg.cmd.action.MultiBonus dailywinbonus;
	public final int winscore;
	public final int losescore;
	public final int drawscore;
	public final java.util.List<cfg.ectype.GradeBonus> weekscorebonus = new java.util.ArrayList<>();
	public final int roundtime;
	public final int timeouttime;
	public final int matchsucccountdown;
	public final java.util.List<Integer> roundvipdeltagroup = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.TeamFightLevelGroup> levelgroups = new java.util.ArrayList<>();
	public final int preparetime;
	public final int completeneedkillnum;
	public final java.util.List<cfg.map.Vector2> bornpositions = new java.util.ArrayList<>();
	public final java.util.List<cfg.map.Vector2> revivepositions = new java.util.ArrayList<>();
	public final int runefirstrefreshtime;
	public final cfg.ectype.Spell runeinfo;
	public final java.util.List<cfg.ectype.Evaluate> evaluate = new java.util.ArrayList<>();
	public TeamFight(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.mainregionid = fs.getInt();
		this.airwallregionid = fs.getInt();
		this.team1bornregionid = fs.getInt();
		this.team2bornregionid = fs.getInt();
		this.levellimit = fs.getInt();
		this.matchnum = fs.getInt();
		this.quitmatchforbidtime = fs.getInt();
		this.petrevivetime = fs.getInt();
		this.broadcastinfo = new cfg.ectype.BroadcastInfo(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.opentimes.add(new cfg.common.DayTimeRange(fs));
		}
		this.extrabonustimes = new cfg.cmd.condition.DayLimit(fs);
		this.dailywintimes = fs.getInt();
		this.dailywinbonus = new cfg.cmd.action.MultiBonus(fs);
		this.winscore = fs.getInt();
		this.losescore = fs.getInt();
		this.drawscore = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.weekscorebonus.add(new cfg.ectype.GradeBonus(fs));
		}
		this.roundtime = fs.getInt();
		this.timeouttime = fs.getInt();
		this.matchsucccountdown = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.roundvipdeltagroup.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.levelgroups.add(new cfg.ectype.TeamFightLevelGroup(fs));
		}
		this.preparetime = fs.getInt();
		this.completeneedkillnum = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bornpositions.add(new cfg.map.Vector2(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.revivepositions.add(new cfg.map.Vector2(fs));
		}
		this.runefirstrefreshtime = fs.getInt();
		this.runeinfo = new cfg.ectype.Spell(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.evaluate.add(new cfg.ectype.Evaluate(fs));
		}
	}
}
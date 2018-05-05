package cfg.ectype;
public final class FamilyTeam extends cfg.CfgObject {
	public final static int TYPEID = -1929185331;
	final public int getTypeId() { return TYPEID; }
	public final int ectypeid;
	public final cfg.cmd.condition.DayLimit rewardfinishnum;
	public final int minteammembernum;
	public final int enteradddbuffid;
	public final int waveinterval;
	public final int mainregionid;
	public final cfg.cmd.action.OneItems showrewards;
	public final java.util.List<cfg.ectype.FamilyTeamLevelMonserInfo> levelmonsterinfos = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.FamilyTeamLevelReward> levelbonus = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.FamilyTeamLevelReward> levelbonus_level= new java.util.HashMap<>();
	public FamilyTeam(cfg.DataStream fs) {
		this.ectypeid = fs.getInt();
		this.rewardfinishnum = new cfg.cmd.condition.DayLimit(fs);
		this.minteammembernum = fs.getInt();
		this.enteradddbuffid = fs.getInt();
		this.waveinterval = fs.getInt();
		this.mainregionid = fs.getInt();
		this.showrewards = new cfg.cmd.action.OneItems(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.levelmonsterinfos.add(new cfg.ectype.FamilyTeamLevelMonserInfo(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.FamilyTeamLevelReward _v = new cfg.ectype.FamilyTeamLevelReward(fs);
			this.levelbonus.add(_v);
			this.levelbonus_level.put(_v.level, _v);
		}
	}
}
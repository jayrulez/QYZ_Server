package cfg.ectype;
public final class SpeedLvMsg extends cfg.CfgObject {
	public final static int TYPEID = -2113136260;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.cmd.condition.MinMaxLevel lv;
	public final cfg.cmd.action.OneItems showbonusid;
	public final int ectypeid;
	public final cfg.map.MonsterSpawn bossref;
	public final int bossregion;
	public final cfg.ectype.TeamRegionInfo team1;
	public final cfg.ectype.TeamRegionInfo team2;
	public final java.util.List<cfg.ectype.SpeedMonsterRefInfo> monsters = new java.util.ArrayList<>();
	public final cfg.ectype.Spell spellmsg;
	public final java.util.List<cfg.ectype.GradeBonus> gradebonus = new java.util.ArrayList<>();
	public SpeedLvMsg(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.lv = new cfg.cmd.condition.MinMaxLevel(fs);
		this.showbonusid = new cfg.cmd.action.OneItems(fs);
		this.ectypeid = fs.getInt();
		this.bossref = new cfg.map.MonsterSpawn(fs);
		this.bossregion = fs.getInt();
		this.team1 = new cfg.ectype.TeamRegionInfo(fs);
		this.team2 = new cfg.ectype.TeamRegionInfo(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsters.add(new cfg.ectype.SpeedMonsterRefInfo(fs));
		}
		this.spellmsg = new cfg.ectype.Spell(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.gradebonus.add(new cfg.ectype.GradeBonus(fs));
		}
	}
}
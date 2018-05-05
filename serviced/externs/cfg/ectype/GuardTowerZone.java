package cfg.ectype;
public final class GuardTowerZone extends cfg.CfgObject {
	public final static int TYPEID = -405938404;
	final public int getTypeId() { return TYPEID; }
	public final int zoneid;
	public final cfg.cmd.condition.MinMaxLevel levellimit;
	public final int ectypeid;
	public final cfg.map.Vector2 baseposition;
	public final int mainregionid;
	public final int baseid;
	public final cfg.cmd.action.MultiBonus winaward;
	public final cfg.cmd.action.MultiBonus maxkillaward;
	public final int hardectypeid;
	public final cfg.map.Vector2 hardbaseposition;
	public final int hardmainregionid;
	public final int hardbaseid;
	public final cfg.cmd.action.MultiBonus hardwinaward;
	public final cfg.cmd.action.MultiBonus hardmaxkillaward;
	public final java.util.List<Integer> showbonusid = new java.util.ArrayList<>();
	public final java.util.List<Integer> hardshowbonusid = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.GuardTowerMonsterInfo> monsterwaves = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.GuardTowerMonsterInfo> monsterwaves_id= new java.util.HashMap<>();
	public final java.util.List<cfg.ectype.GuardTowerMonsterInfo> hardmodemonsters = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.GuardTowerMonsterInfo> hardmodemonsters_id= new java.util.HashMap<>();
	public GuardTowerZone(cfg.DataStream fs) {
		this.zoneid = fs.getInt();
		this.levellimit = new cfg.cmd.condition.MinMaxLevel(fs);
		this.ectypeid = fs.getInt();
		this.baseposition = new cfg.map.Vector2(fs);
		this.mainregionid = fs.getInt();
		this.baseid = fs.getInt();
		this.winaward = new cfg.cmd.action.MultiBonus(fs);
		this.maxkillaward = new cfg.cmd.action.MultiBonus(fs);
		this.hardectypeid = fs.getInt();
		this.hardbaseposition = new cfg.map.Vector2(fs);
		this.hardmainregionid = fs.getInt();
		this.hardbaseid = fs.getInt();
		this.hardwinaward = new cfg.cmd.action.MultiBonus(fs);
		this.hardmaxkillaward = new cfg.cmd.action.MultiBonus(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.showbonusid.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.hardshowbonusid.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.GuardTowerMonsterInfo _v = new cfg.ectype.GuardTowerMonsterInfo(fs);
			this.monsterwaves.add(_v);
			this.monsterwaves_id.put(_v.id, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.GuardTowerMonsterInfo _v = new cfg.ectype.GuardTowerMonsterInfo(fs);
			this.hardmodemonsters.add(_v);
			this.hardmodemonsters_id.put(_v.id, _v);
		}
	}
}
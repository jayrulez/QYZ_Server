package cfg.ectype;
public final class ClimbTowerEctype extends cfg.CfgObject {
	public final static int TYPEID = -1353422280;
	final public int getTypeId() { return TYPEID; }
	public static final int OFFSET_FLOOR_FROM_MAX_FLOOR = 10;
	public static final int OFFLINE_BONUS_MAILID = 11;
	public final int id;
	public final int mode;
	public final int baseid;
	public final cfg.map.Vector2 baseposition;
	public final int mainregionid;
	public final int revivetime;
	public final cfg.cmd.condition.VipLimitsLite dailylimit;
	public final cfg.cmd.condition.MinLevel levellimit;
	public final cfg.ectype.ClimbTowerSweep sweep;
	public final String endword;
	public final java.util.List<cfg.ectype.Buff> buffs = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.Buff> buffs_buffid= new java.util.HashMap<>();
	public final java.util.List<cfg.ectype.FloorInfo> floors = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.FloorInfo> floors_id= new java.util.HashMap<>();
	public ClimbTowerEctype(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.mode = fs.getInt();
		this.baseid = fs.getInt();
		this.baseposition = new cfg.map.Vector2(fs);
		this.mainregionid = fs.getInt();
		this.revivetime = fs.getInt();
		this.dailylimit = new cfg.cmd.condition.VipLimitsLite(fs);
		this.levellimit = new cfg.cmd.condition.MinLevel(fs);
		this.sweep = new cfg.ectype.ClimbTowerSweep(fs);
		this.endword = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.Buff _v = new cfg.ectype.Buff(fs);
			this.buffs.add(_v);
			this.buffs_buffid.put(_v.buffid, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.FloorInfo _v = new cfg.ectype.FloorInfo(fs);
			this.floors.add(_v);
			this.floors_id.put(_v.id, _v);
		}
	}
}
package cfg.role;
public final class BasicStatus extends cfg.CfgObject {
	public final static int TYPEID = 2065026162;
	final public int getTypeId() { return TYPEID; }
	public final int career;
	public final java.util.List<cfg.role.LevelStatusInfo> levelstatusinfo = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.role.LevelStatusInfo> levelstatusinfo_level= new java.util.HashMap<>();
	public BasicStatus(cfg.DataStream fs) {
		this.career = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.role.LevelStatusInfo _v = new cfg.role.LevelStatusInfo(fs);
			this.levelstatusinfo.add(_v);
			this.levelstatusinfo_level.put(_v.level, _v);
		}
	}
}
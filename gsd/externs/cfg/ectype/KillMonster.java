package cfg.ectype;
public final class KillMonster extends cfg.ectype.Action {
	public final static int TYPEID = -905870288;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.ectype.MissionKillMonster> missions = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.MissionKillMonster> missions_monsterid= new java.util.HashMap<>();
	public KillMonster(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.MissionKillMonster _v = new cfg.ectype.MissionKillMonster(fs);
			this.missions.add(_v);
			this.missions_monsterid.put(_v.monsterid, _v);
		}
	}
}
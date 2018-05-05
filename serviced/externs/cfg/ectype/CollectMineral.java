package cfg.ectype;
public final class CollectMineral extends cfg.ectype.Action {
	public final static int TYPEID = -1525242804;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.ectype.MissionCollectMineral> missions = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.MissionCollectMineral> missions_mineralid= new java.util.HashMap<>();
	public CollectMineral(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.MissionCollectMineral _v = new cfg.ectype.MissionCollectMineral(fs);
			this.missions.add(_v);
			this.missions_mineralid.put(_v.mineralid, _v);
		}
	}
}
package cfg.dharmakaya;
public final class nomallevel extends cfg.CfgObject {
	public final static int TYPEID = -1430312502;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final java.util.List<cfg.dharmakaya.DharmakayaLevelInfo> humanstate = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.dharmakaya.DharmakayaLevelInfo> humanstate_level= new java.util.HashMap<>();
	public nomallevel(cfg.DataStream fs) {
		this.id = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.dharmakaya.DharmakayaLevelInfo _v = new cfg.dharmakaya.DharmakayaLevelInfo(fs);
			this.humanstate.add(_v);
			this.humanstate_level.put(_v.level, _v);
		}
	}
}
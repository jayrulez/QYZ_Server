package cfg.pureair;
public final class pureawake extends cfg.CfgObject {
	public final static int TYPEID = 275534567;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.pureair.AirAwakeInfo> humanairawake = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.pureair.AirAwakeInfo> humanairawake_awakelevel= new java.util.HashMap<>();
	public final java.util.List<cfg.pureair.AirAwakeInfo> petairawake = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.pureair.AirAwakeInfo> petairawake_awakelevel= new java.util.HashMap<>();
	public pureawake(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.pureair.AirAwakeInfo _v = new cfg.pureair.AirAwakeInfo(fs);
			this.humanairawake.add(_v);
			this.humanairawake_awakelevel.put(_v.awakelevel, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.pureair.AirAwakeInfo _v = new cfg.pureair.AirAwakeInfo(fs);
			this.petairawake.add(_v);
			this.petairawake_awakelevel.put(_v.awakelevel, _v);
		}
	}
}
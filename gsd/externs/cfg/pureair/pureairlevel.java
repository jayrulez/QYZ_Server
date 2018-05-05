package cfg.pureair;
public final class pureairlevel extends cfg.CfgObject {
	public final static int TYPEID = 1715368920;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.pureair.AirLevel> humanairlevel = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.pureair.AirLevel> humanairlevel_level= new java.util.HashMap<>();
	public final java.util.List<cfg.pureair.AirLevel> petairlevel = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.pureair.AirLevel> petairlevel_level= new java.util.HashMap<>();
	public pureairlevel(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.pureair.AirLevel _v = new cfg.pureair.AirLevel(fs);
			this.humanairlevel.add(_v);
			this.humanairlevel_level.put(_v.level, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.pureair.AirLevel _v = new cfg.pureair.AirLevel(fs);
			this.petairlevel.add(_v);
			this.petairlevel_level.put(_v.level, _v);
		}
	}
}
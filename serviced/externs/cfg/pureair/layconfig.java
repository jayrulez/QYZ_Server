package cfg.pureair;
public final class layconfig extends cfg.CfgObject {
	public final static int TYPEID = 291915584;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.pureair.AirLayInfo> humanairlay = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.pureair.AirLayInfo> humanairlay_lay= new java.util.HashMap<>();
	public final java.util.List<cfg.pureair.AirLayInfo> petairlay = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.pureair.AirLayInfo> petairlay_lay= new java.util.HashMap<>();
	public final String des;
	public layconfig(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.pureair.AirLayInfo _v = new cfg.pureair.AirLayInfo(fs);
			this.humanairlay.add(_v);
			this.humanairlay_lay.put(_v.lay, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.pureair.AirLayInfo _v = new cfg.pureair.AirLayInfo(fs);
			this.petairlay.add(_v);
			this.petairlay_lay.put(_v.lay, _v);
		}
		this.des = fs.getString();
	}
}
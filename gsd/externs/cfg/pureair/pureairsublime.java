package cfg.pureair;
public final class pureairsublime extends cfg.CfgObject {
	public final static int TYPEID = 1559454057;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.pureair.SublimeInfo> humanairsublime = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.pureair.SublimeInfo> humanairsublime_starlevel= new java.util.HashMap<>();
	public final java.util.List<cfg.pureair.SublimeInfo> petairsublime = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.pureair.SublimeInfo> petairsublime_starlevel= new java.util.HashMap<>();
	public pureairsublime(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.pureair.SublimeInfo _v = new cfg.pureair.SublimeInfo(fs);
			this.humanairsublime.add(_v);
			this.humanairsublime_starlevel.put(_v.starlevel, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.pureair.SublimeInfo _v = new cfg.pureair.SublimeInfo(fs);
			this.petairsublime.add(_v);
			this.petairsublime_starlevel.put(_v.starlevel, _v);
		}
	}
}
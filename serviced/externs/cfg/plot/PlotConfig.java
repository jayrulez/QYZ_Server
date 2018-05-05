package cfg.plot;
public final class PlotConfig extends cfg.CfgObject {
	public final static int TYPEID = 2029284678;
	final public int getTypeId() { return TYPEID; }
	public final java.util.Map<Integer, cfg.plot.PlotProfessionConfig> professtionconfig = new java.util.HashMap<>();
	public PlotConfig(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.professtionconfig.put(_k, new cfg.plot.PlotProfessionConfig(fs));
		}
	}
}
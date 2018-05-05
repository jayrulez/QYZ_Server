package cfg.plot;
public final class PlotProfessionConfig extends cfg.CfgObject {
	public final static int TYPEID = -106506142;
	final public int getTypeId() { return TYPEID; }
	public final java.util.Map<Integer, cfg.plot.PlotGenderConfig> genderconfig = new java.util.HashMap<>();
	public PlotProfessionConfig(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.genderconfig.put(_k, new cfg.plot.PlotGenderConfig(fs));
		}
	}
}
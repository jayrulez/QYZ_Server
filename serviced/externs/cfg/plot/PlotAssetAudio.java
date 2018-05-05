package cfg.plot;
public final class PlotAssetAudio extends cfg.plot.PlotAssets {
	public final static int TYPEID = 783507146;
	final public int getTypeId() { return TYPEID; }
	public final String indextable;
	public final String path;
	public final String introduction;
	public final boolean extraasset;
	public PlotAssetAudio(cfg.DataStream fs) {
		super(fs);
		this.indextable = fs.getString();
		this.path = fs.getString();
		this.introduction = fs.getString();
		this.extraasset = fs.getBool();
	}
}
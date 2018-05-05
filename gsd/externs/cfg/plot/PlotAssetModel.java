package cfg.plot;
public final class PlotAssetModel extends cfg.plot.PlotAssets {
	public final static int TYPEID = 794410525;
	final public int getTypeId() { return TYPEID; }
	public final String indextable;
	public final String path;
	public final String introduction;
	public final boolean extraasset;
	public PlotAssetModel(cfg.DataStream fs) {
		super(fs);
		this.indextable = fs.getString();
		this.path = fs.getString();
		this.introduction = fs.getString();
		this.extraasset = fs.getBool();
	}
}
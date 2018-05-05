package cfg.plot;
public abstract class PlotAssets extends cfg.CfgObject {
	public static final float maxloadtime = 5f;
	public static final float maxloadtimeex = 10f;
	public final String index;
	public final int assettype;
	public final String detailtype;
	public PlotAssets(cfg.DataStream fs) {
		this.index = fs.getString();
		this.assettype = fs.getInt();
		this.detailtype = fs.getString();
	}
}
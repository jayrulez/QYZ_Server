package cfg.plot;
public final class PlotQTE extends cfg.CfgObject {
	public final static int TYPEID = -21977314;
	final public int getTypeId() { return TYPEID; }
	public static final float timeScale = 0.005f;
	public final String index;
	public final java.util.List<cfg.plot.QTEButton> buttons = new java.util.ArrayList<>();
	public PlotQTE(cfg.DataStream fs) {
		this.index = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.buttons.add(new cfg.plot.QTEButton(fs));
		}
	}
}
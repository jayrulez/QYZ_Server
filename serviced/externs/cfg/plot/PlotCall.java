package cfg.plot;
public final class PlotCall extends cfg.CfgObject {
	public final static int TYPEID = -681699998;
	final public int getTypeId() { return TYPEID; }
	public final String key;
	public final java.util.List<String> calls = new java.util.ArrayList<>();
	public PlotCall(cfg.DataStream fs) {
		this.key = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.calls.add(fs.getString());
		}
	}
}
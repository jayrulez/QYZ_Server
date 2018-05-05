package cfg.map;
public final class IndexedBezierCurve extends cfg.CfgObject {
	public final static int TYPEID = 1669381723;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.map.BezierCurve curve;
	public IndexedBezierCurve(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.curve = new cfg.map.BezierCurve(fs);
	}
}
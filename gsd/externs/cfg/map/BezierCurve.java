package cfg.map;
public final class BezierCurve extends cfg.CfgObject {
	public final static int TYPEID = 393426452;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.map.BeizerVertex> vertices = new java.util.ArrayList<>();
	public BezierCurve(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.vertices.add(new cfg.map.BeizerVertex(fs));
		}
	}
}
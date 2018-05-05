package cfg.map;
public final class PolygonRegion extends cfg.CfgObject {
	public final static int TYPEID = 382198930;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.map.Vector3> vertices = new java.util.ArrayList<>();
	public PolygonRegion(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.vertices.add(new cfg.map.Vector3(fs));
		}
	}
}
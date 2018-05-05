package cfg.map;
public final class WeightedPolygonRegion extends cfg.CfgObject {
	public final static int TYPEID = -1553777477;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.map.Vector3> vertices = new java.util.ArrayList<>();
	public final int weight;
	public WeightedPolygonRegion(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.vertices.add(new cfg.map.Vector3(fs));
		}
		this.weight = fs.getInt();
	}
}
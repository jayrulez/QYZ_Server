package cfg.map;
public final class MultiPolygon extends cfg.map.DeploymentLocation {
	public final static int TYPEID = 1616354685;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.map.WeightedPolygonRegion> polygons = new java.util.ArrayList<>();
	public MultiPolygon(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.polygons.add(new cfg.map.WeightedPolygonRegion(fs));
		}
	}
}
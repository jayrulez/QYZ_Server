package cfg.map;
public final class EctypeRegionSet extends cfg.CfgObject {
	public final static int TYPEID = 580267418;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final java.util.List<cfg.map.IndexedPolygonRegion> regions = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.map.IndexedPolygonRegion> regions_id= new java.util.HashMap<>();
	public EctypeRegionSet(cfg.DataStream fs) {
		this.id = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.map.IndexedPolygonRegion _v = new cfg.map.IndexedPolygonRegion(fs);
			this.regions.add(_v);
			this.regions_id.put(_v.id, _v);
		}
	}
}
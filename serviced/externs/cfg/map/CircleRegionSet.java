package cfg.map;
public final class CircleRegionSet extends cfg.CfgObject {
	public final static int TYPEID = -533396318;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final java.util.List<cfg.map.IndexedCircleRegion> regions = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.map.IndexedCircleRegion> regions_id= new java.util.HashMap<>();
	public CircleRegionSet(cfg.DataStream fs) {
		this.id = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.map.IndexedCircleRegion _v = new cfg.map.IndexedCircleRegion(fs);
			this.regions.add(_v);
			this.regions_id.put(_v.id, _v);
		}
	}
}
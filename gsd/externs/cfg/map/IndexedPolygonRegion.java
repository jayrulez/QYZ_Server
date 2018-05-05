package cfg.map;
public final class IndexedPolygonRegion extends cfg.CfgObject {
	public final static int TYPEID = -1785432295;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final boolean allowpk;
	public final boolean allowride;
	public final boolean allowtrade;
	public final cfg.map.PolygonRegion polygon;
	public IndexedPolygonRegion(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.allowpk = fs.getBool();
		this.allowride = fs.getBool();
		this.allowtrade = fs.getBool();
		this.polygon = new cfg.map.PolygonRegion(fs);
	}
}
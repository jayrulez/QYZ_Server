package cfg.map;
public final class LandscapeEntrance extends cfg.CfgObject {
	public final static int TYPEID = -1401469867;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.map.PolygonRegion region;
	public LandscapeEntrance(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.region = new cfg.map.PolygonRegion(fs);
	}
}
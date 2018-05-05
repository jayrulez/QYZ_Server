package cfg.task;
public final class LocationRangeData extends cfg.CfgObject {
	public final static int TYPEID = 1319406291;
	final public int getTypeId() { return TYPEID; }
	public final float minx;
	public final float miny;
	public final float minz;
	public final float maxx;
	public final float maxy;
	public final float maxz;
	public final int worldmapid;
	public LocationRangeData(cfg.DataStream fs) {
		this.minx = fs.getFloat();
		this.miny = fs.getFloat();
		this.minz = fs.getFloat();
		this.maxx = fs.getFloat();
		this.maxy = fs.getFloat();
		this.maxz = fs.getFloat();
		this.worldmapid = fs.getInt();
	}
}
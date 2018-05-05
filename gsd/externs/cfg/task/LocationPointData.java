package cfg.task;
public final class LocationPointData extends cfg.CfgObject {
	public final static int TYPEID = -659405402;
	final public int getTypeId() { return TYPEID; }
	public final float x;
	public final float y;
	public final float z;
	public final int worldmapid;
	public LocationPointData(cfg.DataStream fs) {
		this.x = fs.getFloat();
		this.y = fs.getFloat();
		this.z = fs.getFloat();
		this.worldmapid = fs.getInt();
	}
}
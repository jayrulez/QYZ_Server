package cfg.map;
public final class CircleRegion extends cfg.CfgObject {
	public final static int TYPEID = 1450908576;
	final public int getTypeId() { return TYPEID; }
	public final cfg.map.Vector3 center;
	public final float radius;
	public CircleRegion(cfg.DataStream fs) {
		this.center = new cfg.map.Vector3(fs);
		this.radius = fs.getFloat();
	}
}
package cfg.map;
public final class FixedPoint extends cfg.CfgObject {
	public final static int TYPEID = -1797001928;
	final public int getTypeId() { return TYPEID; }
	public final cfg.map.Vector3 position;
	public final cfg.map.Vector3 orientation;
	public FixedPoint(cfg.DataStream fs) {
		this.position = new cfg.map.Vector3(fs);
		this.orientation = new cfg.map.Vector3(fs);
	}
}
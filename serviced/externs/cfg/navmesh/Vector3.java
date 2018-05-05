package cfg.navmesh;
public final class Vector3 extends cfg.CfgObject {
	public final static int TYPEID = 415361864;
	final public int getTypeId() { return TYPEID; }
	public final float x;
	public final float y;
	public final float z;
	public Vector3(cfg.DataStream fs) {
		this.x = fs.getFloat();
		this.y = fs.getFloat();
		this.z = fs.getFloat();
	}
}
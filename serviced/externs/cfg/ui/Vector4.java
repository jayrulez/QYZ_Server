package cfg.ui;
public final class Vector4 extends cfg.CfgObject {
	public final static int TYPEID = -771858015;
	final public int getTypeId() { return TYPEID; }
	public final float x;
	public final float y;
	public final float z;
	public final float w;
	public Vector4(cfg.DataStream fs) {
		this.x = fs.getFloat();
		this.y = fs.getFloat();
		this.z = fs.getFloat();
		this.w = fs.getFloat();
	}
}
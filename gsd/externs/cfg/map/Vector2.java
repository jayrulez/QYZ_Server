package cfg.map;
public final class Vector2 extends cfg.CfgObject {
	public final static int TYPEID = 198261267;
	final public int getTypeId() { return TYPEID; }
	public final float x;
	public final float y;
	public Vector2(cfg.DataStream fs) {
		this.x = fs.getFloat();
		this.y = fs.getFloat();
	}
}
package cfg.ui;
public final class Rect extends cfg.CfgObject {
	public final static int TYPEID = 516415892;
	final public int getTypeId() { return TYPEID; }
	public final float left;
	public final float top;
	public final float width;
	public final float height;
	public Rect(cfg.DataStream fs) {
		this.left = fs.getFloat();
		this.top = fs.getFloat();
		this.width = fs.getFloat();
		this.height = fs.getFloat();
	}
}
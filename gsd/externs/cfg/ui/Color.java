package cfg.ui;
public final class Color extends cfg.CfgObject {
	public final static int TYPEID = -1184522829;
	final public int getTypeId() { return TYPEID; }
	public final float a;
	public final float b;
	public final float g;
	public final float r;
	public Color(cfg.DataStream fs) {
		this.a = fs.getFloat();
		this.b = fs.getFloat();
		this.g = fs.getFloat();
		this.r = fs.getFloat();
	}
}
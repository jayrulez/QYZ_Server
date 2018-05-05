package cfg.reborn;
public final class Transparency extends cfg.CfgObject {
	public final static int TYPEID = 1318164384;
	final public int getTypeId() { return TYPEID; }
	public final float mintransparency;
	public final float maxtransparency;
	public Transparency(cfg.DataStream fs) {
		this.mintransparency = fs.getFloat();
		this.maxtransparency = fs.getFloat();
	}
}
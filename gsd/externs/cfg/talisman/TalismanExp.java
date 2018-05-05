package cfg.talisman;
public final class TalismanExp extends cfg.CfgObject {
	public final static int TYPEID = 1845987609;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final int requireexp;
	public final float maturerate;
	public final float specialattackrate;
	public TalismanExp(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.requireexp = fs.getInt();
		this.maturerate = fs.getFloat();
		this.specialattackrate = fs.getFloat();
	}
}
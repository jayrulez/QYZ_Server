package cfg.dharmakaya;
public final class BreakThroughCrit extends cfg.CfgObject {
	public final static int TYPEID = 1700913661;
	final public int getTypeId() { return TYPEID; }
	public final int magnification;
	public final float critrate;
	public BreakThroughCrit(cfg.DataStream fs) {
		this.magnification = fs.getInt();
		this.critrate = fs.getFloat();
	}
}
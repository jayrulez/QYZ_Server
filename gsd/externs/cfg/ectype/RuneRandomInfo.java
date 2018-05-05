package cfg.ectype;
public final class RuneRandomInfo extends cfg.CfgObject {
	public final static int TYPEID = -2032650025;
	final public int getTypeId() { return TYPEID; }
	public final int rune;
	public final int droprate;
	public RuneRandomInfo(cfg.DataStream fs) {
		this.rune = fs.getInt();
		this.droprate = fs.getInt();
	}
}
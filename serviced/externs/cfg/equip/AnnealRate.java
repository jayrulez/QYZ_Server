package cfg.equip;
public final class AnnealRate extends cfg.CfgObject {
	public final static int TYPEID = -649771977;
	final public int getTypeId() { return TYPEID; }
	public static final int RATE_BASE_NUMBER = 10000;
	public final int level;
	public final int rate;
	public AnnealRate(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.rate = fs.getInt();
	}
}
package cfg.bonus;
public final class ContinuityLogin extends cfg.CfgObject {
	public final static int TYPEID = 1761281760;
	final public int getTypeId() { return TYPEID; }
	public final int dayscount;
	public final int opentimes;
	public ContinuityLogin(cfg.DataStream fs) {
		this.dayscount = fs.getInt();
		this.opentimes = fs.getInt();
	}
}
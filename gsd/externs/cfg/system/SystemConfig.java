package cfg.system;
public final class SystemConfig extends cfg.CfgObject {
	public final static int TYPEID = -105283770;
	final public int getTypeId() { return TYPEID; }
	public final int dayidlehour;
	public final int keepaliveinterval;
	public SystemConfig(cfg.DataStream fs) {
		this.dayidlehour = fs.getInt();
		this.keepaliveinterval = fs.getInt();
	}
}
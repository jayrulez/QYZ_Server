package cfg.active;
public final class FindBackCandoTimes extends cfg.CfgObject {
	public final static int TYPEID = -1506958071;
	final public int getTypeId() { return TYPEID; }
	public final int eventtype;
	public final String desc;
	public final cfg.cmd.condition.VipLimitsLite candotimes;
	public FindBackCandoTimes(cfg.DataStream fs) {
		this.eventtype = fs.getInt();
		this.desc = fs.getString();
		this.candotimes = new cfg.cmd.condition.VipLimitsLite(fs);
	}
}
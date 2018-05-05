package cfg.lottery;
public final class LotteryTypeToCfg extends cfg.CfgObject {
	public final static int TYPEID = -1186857483;
	final public int getTypeId() { return TYPEID; }
	public final int type;
	public final int cfgid;
	public LotteryTypeToCfg(cfg.DataStream fs) {
		this.type = fs.getInt();
		this.cfgid = fs.getInt();
	}
}
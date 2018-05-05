package cfg.cmd.action;
public final class ChongZhiJiFen extends cfg.cmd.action.FixCurrency {
	public final static int TYPEID = 1005119806;
	final public int getTypeId() { return TYPEID; }
	public ChongZhiJiFen(cfg.DataStream fs) {
		super(fs);
	}
}
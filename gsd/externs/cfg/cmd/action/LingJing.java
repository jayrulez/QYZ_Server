package cfg.cmd.action;
public final class LingJing extends cfg.cmd.action.FixCurrency {
	public final static int TYPEID = 1453001704;
	final public int getTypeId() { return TYPEID; }
	public LingJing(cfg.DataStream fs) {
		super(fs);
	}
}
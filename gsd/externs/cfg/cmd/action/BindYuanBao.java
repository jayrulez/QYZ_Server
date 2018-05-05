package cfg.cmd.action;
public final class BindYuanBao extends cfg.cmd.action.FixCurrency {
	public final static int TYPEID = -1985959376;
	final public int getTypeId() { return TYPEID; }
	public BindYuanBao(cfg.DataStream fs) {
		super(fs);
	}
}
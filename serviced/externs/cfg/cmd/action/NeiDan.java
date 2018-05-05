package cfg.cmd.action;
public final class NeiDan extends cfg.cmd.action.FixCurrency {
	public final static int TYPEID = 595675833;
	final public int getTypeId() { return TYPEID; }
	public NeiDan(cfg.DataStream fs) {
		super(fs);
	}
}
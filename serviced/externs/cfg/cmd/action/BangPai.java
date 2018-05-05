package cfg.cmd.action;
public final class BangPai extends cfg.cmd.action.FixCurrency {
	public final static int TYPEID = -882900890;
	final public int getTypeId() { return TYPEID; }
	public BangPai(cfg.DataStream fs) {
		super(fs);
	}
}
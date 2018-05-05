package cfg.cmd.action;
public final class LotteryScore extends cfg.cmd.action.FixCurrency {
	public final static int TYPEID = -635221149;
	final public int getTypeId() { return TYPEID; }
	public LotteryScore(cfg.DataStream fs) {
		super(fs);
	}
}
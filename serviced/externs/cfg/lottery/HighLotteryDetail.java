package cfg.lottery;
public final class HighLotteryDetail extends cfg.CfgObject {
	public final static int TYPEID = 1004101097;
	final public int getTypeId() { return TYPEID; }
	public final int lotterycount;
	public final int requiremultiple;
	public final cfg.cmd.action.MultiBonus bonuslist;
	public HighLotteryDetail(cfg.DataStream fs) {
		this.lotterycount = fs.getInt();
		this.requiremultiple = fs.getInt();
		this.bonuslist = new cfg.cmd.action.MultiBonus(fs);
	}
}
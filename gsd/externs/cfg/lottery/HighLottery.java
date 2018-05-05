package cfg.lottery;
public final class HighLottery extends cfg.CfgObject {
	public final static int TYPEID = 1229509496;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.cmd.condition.Currency requirecurrency;
	public final cfg.cmd.condition.OneItem requireitem;
	public final cfg.cmd.condition.OneItem requireitem2;
	public final cfg.cmd.condition.DayLimit refreshtimes;
	public final cfg.cmd.condition.CoolDown refreshinterval;
	public final cfg.cmd.action.Currency recievedcurrency;
	public final java.util.List<cfg.lottery.HighLotteryDetail> lotterylist = new java.util.ArrayList<>();
	public HighLottery(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.requirecurrency = new cfg.cmd.condition.Currency(fs);
		this.requireitem = new cfg.cmd.condition.OneItem(fs);
		this.requireitem2 = new cfg.cmd.condition.OneItem(fs);
		this.refreshtimes = new cfg.cmd.condition.DayLimit(fs);
		this.refreshinterval = new cfg.cmd.condition.CoolDown(fs);
		this.recievedcurrency = new cfg.cmd.action.Currency(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.lotterylist.add(new cfg.lottery.HighLotteryDetail(fs));
		}
	}
}
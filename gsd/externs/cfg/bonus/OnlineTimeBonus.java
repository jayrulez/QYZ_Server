package cfg.bonus;
public final class OnlineTimeBonus extends cfg.CfgObject {
	public final static int TYPEID = 735373894;
	final public int getTypeId() { return TYPEID; }
	public final int onlinetimes;
	public final cfg.cmd.action.RandomBonus bonuslist;
	public OnlineTimeBonus(cfg.DataStream fs) {
		this.onlinetimes = fs.getInt();
		this.bonuslist = new cfg.cmd.action.RandomBonus(fs);
	}
}
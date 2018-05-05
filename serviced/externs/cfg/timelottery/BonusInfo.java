package cfg.timelottery;
public final class BonusInfo extends cfg.CfgObject {
	public final static int TYPEID = -497927247;
	final public int getTypeId() { return TYPEID; }
	public final int pos;
	public final int showbonusid;
	public final int amount;
	public final int weight;
	public final cfg.cmd.action.MultiBonus bonus;
	public BonusInfo(cfg.DataStream fs) {
		this.pos = fs.getInt();
		this.showbonusid = fs.getInt();
		this.amount = fs.getInt();
		this.weight = fs.getInt();
		this.bonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
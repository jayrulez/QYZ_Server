package cfg.bonus;
public final class MonthBonus extends cfg.CfgObject {
	public final static int TYPEID = -1008453864;
	final public int getTypeId() { return TYPEID; }
	public static final int RETROACTIVE_CURRENCY = 10200002;
	public final int dayscount;
	public final cfg.cmd.condition.MinVipLevel requireviplevel;
	public final cfg.cmd.action.Item bonuslist;
	public MonthBonus(cfg.DataStream fs) {
		this.dayscount = fs.getInt();
		this.requireviplevel = new cfg.cmd.condition.MinVipLevel(fs);
		this.bonuslist = new cfg.cmd.action.Item(fs);
	}
}
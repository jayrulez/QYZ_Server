package cfg.bonus;
public final class MonthlyCard extends cfg.CfgObject {
	public final static int TYPEID = 13060676;
	final public int getTypeId() { return TYPEID; }
	public final int dayscount;
	public final cfg.cmd.action.MultiBonus bonus;
	public MonthlyCard(cfg.DataStream fs) {
		this.dayscount = fs.getInt();
		this.bonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
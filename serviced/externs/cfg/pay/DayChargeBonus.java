package cfg.pay;
public final class DayChargeBonus extends cfg.CfgObject {
	public final static int TYPEID = 623854719;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int groupid;
	public final String description;
	public final String unfinishlabel;
	public final String finishedlabel;
	public final int num;
	public final cfg.cmd.action.MultiBonus reward;
	public DayChargeBonus(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.groupid = fs.getInt();
		this.description = fs.getString();
		this.unfinishlabel = fs.getString();
		this.finishedlabel = fs.getString();
		this.num = fs.getInt();
		this.reward = new cfg.cmd.action.MultiBonus(fs);
	}
}
package cfg.bonus;
public final class BeginnerBonus extends cfg.CfgObject {
	public final static int TYPEID = 1140941492;
	final public int getTypeId() { return TYPEID; }
	public final int dayscount;
	public final cfg.cmd.action.Items bonuslist;
	public BeginnerBonus(cfg.DataStream fs) {
		this.dayscount = fs.getInt();
		this.bonuslist = new cfg.cmd.action.Items(fs);
	}
}
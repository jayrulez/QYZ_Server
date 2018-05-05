package cfg.family;
public final class Pray extends cfg.CfgObject {
	public final static int TYPEID = -661179494;
	final public int getTypeId() { return TYPEID; }
	public final int prayid;
	public final String prayname;
	public final cfg.cmd.action.BangGong familycontribution;
	public final cfg.cmd.action.AddFamilyMoneyBuild familycapital;
	public final cfg.cmd.condition.VipLimitsLite daylimit;
	public final cfg.cmd.condition.FixCurrency cost;
	public Pray(cfg.DataStream fs) {
		this.prayid = fs.getInt();
		this.prayname = fs.getString();
		this.familycontribution = new cfg.cmd.action.BangGong(fs);
		this.familycapital = new cfg.cmd.action.AddFamilyMoneyBuild(fs);
		this.daylimit = new cfg.cmd.condition.VipLimitsLite(fs);
		this.cost = (cfg.cmd.condition.FixCurrency)fs.getObject(fs.getString());
	}
}
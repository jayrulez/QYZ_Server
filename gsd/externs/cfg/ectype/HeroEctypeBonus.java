package cfg.ectype;
public final class HeroEctypeBonus extends cfg.CfgObject {
	public final static int TYPEID = 966995297;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.Currency cost;
	public final cfg.cmd.action.Bonus bonus;
	public HeroEctypeBonus(cfg.DataStream fs) {
		this.cost = new cfg.cmd.condition.Currency(fs);
		this.bonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}
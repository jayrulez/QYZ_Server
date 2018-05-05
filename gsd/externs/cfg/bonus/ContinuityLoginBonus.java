package cfg.bonus;
public final class ContinuityLoginBonus extends cfg.CfgObject {
	public final static int TYPEID = -1156811489;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.RandomBonus bonuslist;
	public ContinuityLoginBonus(cfg.DataStream fs) {
		this.bonuslist = new cfg.cmd.action.RandomBonus(fs);
	}
}
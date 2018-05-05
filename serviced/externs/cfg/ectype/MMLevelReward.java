package cfg.ectype;
public final class MMLevelReward extends cfg.CfgObject {
	public final static int TYPEID = 401800903;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final cfg.cmd.action.MultiBonus bonus;
	public MMLevelReward(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.bonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
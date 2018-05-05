package cfg.family;
public final class BossChallenge extends cfg.CfgObject {
	public final static int TYPEID = -567708266;
	final public int getTypeId() { return TYPEID; }
	public final int bosslevel;
	public final cfg.cmd.condition.FamilyMoney bossrequirecapital;
	public BossChallenge(cfg.DataStream fs) {
		this.bosslevel = fs.getInt();
		this.bossrequirecapital = new cfg.cmd.condition.FamilyMoney(fs);
	}
}
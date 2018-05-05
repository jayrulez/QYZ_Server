package cfg.bonus;
public final class FamilyBonus extends cfg.CfgObject {
	public final static int TYPEID = 1852173474;
	final public int getTypeId() { return TYPEID; }
	public final int bonusid;
	public final cfg.cmd.condition.MinFamilyLevel requirefamilylvl;
	public final cfg.cmd.condition.Limit weekbonustimes;
	public final cfg.cmd.action.Items bonus;
	public FamilyBonus(cfg.DataStream fs) {
		this.bonusid = fs.getInt();
		this.requirefamilylvl = new cfg.cmd.condition.MinFamilyLevel(fs);
		this.weekbonustimes = new cfg.cmd.condition.Limit(fs);
		this.bonus = new cfg.cmd.action.Items(fs);
	}
}
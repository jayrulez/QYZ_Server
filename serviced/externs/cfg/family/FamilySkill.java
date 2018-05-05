package cfg.family;
public final class FamilySkill extends cfg.CfgObject {
	public final static int TYPEID = -2002743443;
	final public int getTypeId() { return TYPEID; }
	public final int skilllvl;
	public final cfg.cmd.condition.FamilyMoney requirefamilycapital;
	public final cfg.cmd.condition.MinFamilyLevel requirefamilylvl;
	public FamilySkill(cfg.DataStream fs) {
		this.skilllvl = fs.getInt();
		this.requirefamilycapital = new cfg.cmd.condition.FamilyMoney(fs);
		this.requirefamilylvl = new cfg.cmd.condition.MinFamilyLevel(fs);
	}
}
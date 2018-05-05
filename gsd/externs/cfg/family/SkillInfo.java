package cfg.family;
public final class SkillInfo extends cfg.CfgObject {
	public final static int TYPEID = 2021108927;
	final public int getTypeId() { return TYPEID; }
	public final cfg.equip.EquipPropertyData property;
	public final cfg.cmd.condition.FamilyMoney requirefamilycapital;
	public SkillInfo(cfg.DataStream fs) {
		this.property = new cfg.equip.EquipPropertyData(fs);
		this.requirefamilycapital = new cfg.cmd.condition.FamilyMoney(fs);
	}
}
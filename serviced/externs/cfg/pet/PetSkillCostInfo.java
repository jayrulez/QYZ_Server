package cfg.pet;
public final class PetSkillCostInfo extends cfg.CfgObject {
	public final static int TYPEID = -522441466;
	final public int getTypeId() { return TYPEID; }
	public final int requirelvl;
	public final int cost;
	public PetSkillCostInfo(cfg.DataStream fs) {
		this.requirelvl = fs.getInt();
		this.cost = fs.getInt();
	}
}
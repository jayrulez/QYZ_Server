package cfg.pet;
public final class GetSkill extends cfg.pet.AwakeEffect {
	public final static int TYPEID = -1356769804;
	final public int getTypeId() { return TYPEID; }
	public final int skillid;
	public GetSkill(cfg.DataStream fs) {
		super(fs);
		this.skillid = fs.getInt();
	}
}
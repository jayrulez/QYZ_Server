package cfg.pet;
public final class PetSkillLvlupData extends cfg.CfgObject {
	public final static int TYPEID = -642434564;
	final public int getTypeId() { return TYPEID; }
	public final int requirepetlvl;
	public final java.util.Map<Integer, Long> cost = new java.util.HashMap<>();
	public PetSkillLvlupData(cfg.DataStream fs) {
		this.requirepetlvl = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.cost.put(_k, fs.getLong());
		}
	}
}
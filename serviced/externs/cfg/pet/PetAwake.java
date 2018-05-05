package cfg.pet;
public final class PetAwake extends cfg.CfgObject {
	public final static int TYPEID = 1427347711;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final java.util.List<cfg.pet.AwakeInfo> awakelvlup = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.pet.AwakeInfo> awakelvlup_awakeid= new java.util.HashMap<>();
	public PetAwake(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.pet.AwakeInfo _v = new cfg.pet.AwakeInfo(fs);
			this.awakelvlup.add(_v);
			this.awakelvlup_awakeid.put(_v.awakeid, _v);
		}
	}
}
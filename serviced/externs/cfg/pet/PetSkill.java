package cfg.pet;
public final class PetSkill extends cfg.CfgObject {
	public final static int TYPEID = 1443621323;
	final public int getTypeId() { return TYPEID; }
	public final int petid;
	public final String name;
	public final java.util.List<Integer> skilllist = new java.util.ArrayList<>();
	public final java.util.List<Integer> awakeskill = new java.util.ArrayList<>();
	public PetSkill(cfg.DataStream fs) {
		this.petid = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.skilllist.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.awakeskill.add(fs.getInt());
		}
	}
}
package cfg.equip;
public final class AmuletSkillLvl extends cfg.CfgObject {
	public final static int TYPEID = -1550055733;
	final public int getTypeId() { return TYPEID; }
	public final int skillid;
	public final int skillid2;
	public final int carrer;
	public final java.util.List<Integer> weightlist = new java.util.ArrayList<>();
	public AmuletSkillLvl(cfg.DataStream fs) {
		this.skillid = fs.getInt();
		this.skillid2 = fs.getInt();
		this.carrer = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.weightlist.add(fs.getInt());
		}
	}
}
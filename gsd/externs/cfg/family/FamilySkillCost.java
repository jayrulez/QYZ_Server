package cfg.family;
public final class FamilySkillCost extends cfg.CfgObject {
	public final static int TYPEID = 501298394;
	final public int getTypeId() { return TYPEID; }
	public final int skillid;
	public final String name;
	public final String icon;
	public final java.util.List<cfg.family.SkillInfo> skillinfo = new java.util.ArrayList<>();
	public FamilySkillCost(cfg.DataStream fs) {
		this.skillid = fs.getInt();
		this.name = fs.getString();
		this.icon = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.skillinfo.add(new cfg.family.SkillInfo(fs));
		}
	}
}
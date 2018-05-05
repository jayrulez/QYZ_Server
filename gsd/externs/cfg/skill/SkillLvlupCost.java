package cfg.skill;
public final class SkillLvlupCost extends cfg.CfgObject {
	public final static int TYPEID = 1513480448;
	final public int getTypeId() { return TYPEID; }
	public final int skillid;
	public final String owner;
	public final int nextskillid;
	public final int requireawakelvl;
	public final java.util.List<cfg.skill.SkillLvlupData> skilllvlupdata = new java.util.ArrayList<>();
	public SkillLvlupCost(cfg.DataStream fs) {
		this.skillid = fs.getInt();
		this.owner = fs.getString();
		this.nextskillid = fs.getInt();
		this.requireawakelvl = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.skilllvlupdata.add(new cfg.skill.SkillLvlupData(fs));
		}
	}
}
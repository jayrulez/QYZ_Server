package cfg.skill;
public final class SkillDescribe extends cfg.CfgObject {
	public final static int TYPEID = -1087880523;
	final public int getTypeId() { return TYPEID; }
	public final int skillid;
	public final String name;
	public final java.util.List<String> description = new java.util.ArrayList<>();
	public SkillDescribe(cfg.DataStream fs) {
		this.skillid = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.description.add(fs.getString());
		}
	}
}
package cfg.role;
public final class SkillPriorityConfig extends cfg.CfgObject {
	public final static int TYPEID = -667117719;
	final public int getTypeId() { return TYPEID; }
	public final int career;
	public final java.util.List<Integer> skillpriority = new java.util.ArrayList<>();
	public SkillPriorityConfig(cfg.DataStream fs) {
		this.career = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.skillpriority.add(fs.getInt());
		}
	}
}
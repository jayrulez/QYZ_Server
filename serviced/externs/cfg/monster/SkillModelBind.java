package cfg.monster;
public final class SkillModelBind extends cfg.CfgObject {
	public final static int TYPEID = 876461363;
	final public int getTypeId() { return TYPEID; }
	public final String modelname;
	public final float attackrange;
	public final java.util.List<Integer> skills = new java.util.ArrayList<>();
	public SkillModelBind(cfg.DataStream fs) {
		this.modelname = fs.getString();
		this.attackrange = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.skills.add(fs.getInt());
		}
	}
}
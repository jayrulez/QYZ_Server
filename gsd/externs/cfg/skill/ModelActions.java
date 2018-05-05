package cfg.skill;
public final class ModelActions extends cfg.CfgObject {
	public final static int TYPEID = -2069019653;
	final public int getTypeId() { return TYPEID; }
	public final String modelname;
	public final String basemodelname;
	public final String templatemodelname;
	public final java.util.Map<String, cfg.skill.ModelAction> actions = new java.util.HashMap<>();
	public final java.util.Map<String, cfg.skill.SkillAction> skillactions = new java.util.HashMap<>();
	public ModelActions(cfg.DataStream fs) {
		this.modelname = fs.getString();
		this.basemodelname = fs.getString();
		this.templatemodelname = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final String _k = fs.getString();
			this.actions.put(_k, new cfg.skill.ModelAction(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final String _k = fs.getString();
			this.skillactions.put(_k, new cfg.skill.SkillAction(fs));
		}
	}
}
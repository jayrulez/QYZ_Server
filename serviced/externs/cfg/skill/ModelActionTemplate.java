package cfg.skill;
public final class ModelActionTemplate extends cfg.CfgObject {
	public final static int TYPEID = 572941330;
	final public int getTypeId() { return TYPEID; }
	public final String modelname;
	public final java.util.Map<String, String> actions = new java.util.HashMap<>();
	public ModelActionTemplate(cfg.DataStream fs) {
		this.modelname = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final String _k = fs.getString();
			this.actions.put(_k, fs.getString());
		}
	}
}
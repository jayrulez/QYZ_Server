package cfg.skill;
public abstract class ModelActionBase extends cfg.CfgObject {
	public final String actionname;
	public final int actionsourcetype;
	public final String othermodelname;
	public final String actionfile;
	public final String foreactionfile;
	public final String succactionfile;
	public final float actionspeed;
	public final int loopplay;
	public final int effectid;
	public final java.util.List<cfg.skill.Action> actions = new java.util.ArrayList<>();
	public final java.util.List<cfg.skill.Effect> effects = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.skill.Effect> effects_id= new java.util.HashMap<>();
	public ModelActionBase(cfg.DataStream fs) {
		this.actionname = fs.getString();
		this.actionsourcetype = fs.getInt();
		this.othermodelname = fs.getString();
		this.actionfile = fs.getString();
		this.foreactionfile = fs.getString();
		this.succactionfile = fs.getString();
		this.actionspeed = fs.getFloat();
		this.loopplay = fs.getInt();
		this.effectid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.actions.add((cfg.skill.Action)fs.getObject(fs.getString()));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.skill.Effect _v = new cfg.skill.Effect(fs);
			this.effects.add(_v);
			this.effects_id.put(_v.id, _v);
		}
	}
}
package cfg.skill;
public abstract class Action extends cfg.CfgObject {
	public final float timeline;
	public Action(cfg.DataStream fs) {
		this.timeline = fs.getFloat();
	}
}
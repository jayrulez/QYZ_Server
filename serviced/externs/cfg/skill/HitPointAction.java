package cfg.skill;
public abstract class HitPointAction extends cfg.CfgObject {
	public final int target;
	public HitPointAction(cfg.DataStream fs) {
		this.target = fs.getInt();
	}
}
package cfg.operationalactivity;
public final class ActivityEntry extends cfg.CfgObject {
	public final static int TYPEID = -1272427220;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.operationalactivity.ActivityCondition condition;
	public final cfg.cmd.action.MultiBonus reward;
	public ActivityEntry(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.condition = (cfg.operationalactivity.ActivityCondition)fs.getObject(fs.getString());
		this.reward = new cfg.cmd.action.MultiBonus(fs);
	}
}
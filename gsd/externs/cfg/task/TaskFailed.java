package cfg.task;
public final class TaskFailed extends cfg.CfgObject {
	public final static int TYPEID = -627022655;
	final public int getTypeId() { return TYPEID; }
	public final boolean roledead;
	public final boolean playeroffline;
	public TaskFailed(cfg.DataStream fs) {
		this.roledead = fs.getBool();
		this.playeroffline = fs.getBool();
	}
}
package cfg.cmd.condition;
public final class GroupCoolDown extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 353298489;
	final public int getTypeId() { return TYPEID; }
	public final int groupid;
	public final float time;
	public GroupCoolDown(cfg.DataStream fs) {
		super(fs);
		this.groupid = fs.getInt();
		this.time = fs.getFloat();
	}
}
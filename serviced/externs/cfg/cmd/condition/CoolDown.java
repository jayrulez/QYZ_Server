package cfg.cmd.condition;
public final class CoolDown extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -529806052;
	final public int getTypeId() { return TYPEID; }
	public final float time;
	public CoolDown(cfg.DataStream fs) {
		super(fs);
		this.time = fs.getFloat();
	}
}
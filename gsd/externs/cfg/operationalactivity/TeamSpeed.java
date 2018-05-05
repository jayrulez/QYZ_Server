package cfg.operationalactivity;
public final class TeamSpeed extends cfg.operationalactivity.SimpleCondition {
	public final static int TYPEID = 43123443;
	final public int getTypeId() { return TYPEID; }
	public TeamSpeed(cfg.DataStream fs) {
		super(fs);
	}
}
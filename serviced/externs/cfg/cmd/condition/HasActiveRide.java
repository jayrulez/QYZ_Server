package cfg.cmd.condition;
public final class HasActiveRide extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 1581113447;
	final public int getTypeId() { return TYPEID; }
	public final boolean has;
	public HasActiveRide(cfg.DataStream fs) {
		super(fs);
		this.has = fs.getBool();
	}
}
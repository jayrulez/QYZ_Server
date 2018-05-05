package cfg.cmd.condition;
public final class MinFamilyLevel extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 591735199;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public MinFamilyLevel(cfg.DataStream fs) {
		super(fs);
		this.level = fs.getInt();
	}
}
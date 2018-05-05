package cfg.cmd.condition;
public final class PetLevel extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 481371958;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public PetLevel(cfg.DataStream fs) {
		super(fs);
		this.level = fs.getInt();
	}
}
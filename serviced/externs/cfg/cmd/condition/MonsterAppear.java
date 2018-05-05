package cfg.cmd.condition;
public final class MonsterAppear extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -2062665954;
	final public int getTypeId() { return TYPEID; }
	public final int monsterid;
	public final float distance;
	public MonsterAppear(cfg.DataStream fs) {
		super(fs);
		this.monsterid = fs.getInt();
		this.distance = fs.getFloat();
	}
}
package cfg.skill;
public final class Movement extends cfg.skill.Action {
	public final static int TYPEID = -538453354;
	final public int getTypeId() { return TYPEID; }
	public static final int MoveBack = 2;
	public static final int MoveToTarget = 1;
	public static final int MoveInDirection = 0;
	public final float duration;
	public final float speed;
	public final float acceleration;
	public final int type;
	public Movement(cfg.DataStream fs) {
		super(fs);
		this.duration = fs.getFloat();
		this.speed = fs.getFloat();
		this.acceleration = fs.getFloat();
		this.type = fs.getInt();
	}
}
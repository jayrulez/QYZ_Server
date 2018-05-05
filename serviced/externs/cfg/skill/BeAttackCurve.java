package cfg.skill;
public final class BeAttackCurve extends cfg.CfgObject {
	public final static int TYPEID = 105607613;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String note;
	public final String Ename;
	public final int mass;
	public final int typeid;
	public final float velocity;
	public final String randV;
	public final float angle;
	public final float distance;
	public final String randDX;
	public final String randDY;
	public final String randDZ;
	public final float angleToGather;
	public final float gravity;
	public final float friction;
	public final float defaultGravity;
	public final int decay;
	public final String actionInTheAir;
	public final String actionToClimb;
	public final float maxTime;
	public final int shakeID;
	public final int Time;
	public BeAttackCurve(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.note = fs.getString();
		this.Ename = fs.getString();
		this.mass = fs.getInt();
		this.typeid = fs.getInt();
		this.velocity = fs.getFloat();
		this.randV = fs.getString();
		this.angle = fs.getFloat();
		this.distance = fs.getFloat();
		this.randDX = fs.getString();
		this.randDY = fs.getString();
		this.randDZ = fs.getString();
		this.angleToGather = fs.getFloat();
		this.gravity = fs.getFloat();
		this.friction = fs.getFloat();
		this.defaultGravity = fs.getFloat();
		this.decay = fs.getInt();
		this.actionInTheAir = fs.getString();
		this.actionToClimb = fs.getString();
		this.maxTime = fs.getFloat();
		this.shakeID = fs.getInt();
		this.Time = fs.getInt();
	}
}
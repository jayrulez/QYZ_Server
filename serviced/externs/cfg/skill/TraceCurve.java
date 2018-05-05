package cfg.skill;
public final class TraceCurve extends cfg.CfgObject {
	public final static int TYPEID = -245162287;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final float velocity;
	public final float angle;
	public final float hacc;
	public final float vacc;
	public final float hbounceacc;
	public final float vbounceacc;
	public final float bouncecoe;
	public TraceCurve(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.velocity = fs.getFloat();
		this.angle = fs.getFloat();
		this.hacc = fs.getFloat();
		this.vacc = fs.getFloat();
		this.hbounceacc = fs.getFloat();
		this.vbounceacc = fs.getFloat();
		this.bouncecoe = fs.getFloat();
	}
}
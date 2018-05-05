package cfg.skill;
public final class FlyWeapon extends cfg.skill.TraceObject {
	public final static int TYPEID = 1575828328;
	final public int getTypeId() { return TYPEID; }
	public final float bulletradius;
	public final boolean passbody;
	public final int beattackeffectid;
	public FlyWeapon(cfg.DataStream fs) {
		super(fs);
		this.bulletradius = fs.getFloat();
		this.passbody = fs.getBool();
		this.beattackeffectid = fs.getInt();
	}
}
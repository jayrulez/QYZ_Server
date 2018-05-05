package cfg.equip;
public final class FlyingSwordHeight extends cfg.CfgObject {
	public final static int TYPEID = -1987938931;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int faction;
	public final int gender;
	public final float offsety;
	public FlyingSwordHeight(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.faction = fs.getInt();
		this.gender = fs.getInt();
		this.offsety = fs.getFloat();
	}
}
package cfg.equip;
public final class Weapon extends cfg.equip.MainEquip {
	public final static int TYPEID = -1973632252;
	final public int getTypeId() { return TYPEID; }
	public final String left;
	public final String right;
	public Weapon(cfg.DataStream fs) {
		super(fs);
		this.left = fs.getString();
		this.right = fs.getString();
	}
}
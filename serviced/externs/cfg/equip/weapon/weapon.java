package cfg.equip.weapon;
public final class weapon extends cfg.CfgObject {
	public final static int TYPEID = -1654405018;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String weaponname;
	public final String lpath;
	public final String rpath;
	public weapon(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.weaponname = fs.getString();
		this.lpath = fs.getString();
		this.rpath = fs.getString();
	}
}
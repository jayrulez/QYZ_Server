package cfg.role;
public final class Profession extends cfg.CfgObject {
	public final static int TYPEID = 1653976074;
	final public int getTypeId() { return TYPEID; }
	public final int faction;
	public final String name;
	public final String icon;
	public final boolean isopen;
	public final String modelname;
	public final String modelname2;
	public final int weight;
	public final String talismanactionname;
	public final int defaultweaponid;
	public final int createweaponid;
	public final int createarmourid;
	public Profession(cfg.DataStream fs) {
		this.faction = fs.getInt();
		this.name = fs.getString();
		this.icon = fs.getString();
		this.isopen = fs.getBool();
		this.modelname = fs.getString();
		this.modelname2 = fs.getString();
		this.weight = fs.getInt();
		this.talismanactionname = fs.getString();
		this.defaultweaponid = fs.getInt();
		this.createweaponid = fs.getInt();
		this.createarmourid = fs.getInt();
	}
}
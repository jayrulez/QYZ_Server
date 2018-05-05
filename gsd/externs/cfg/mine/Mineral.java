package cfg.mine;
public final class Mineral extends cfg.CfgObject {
	public final static int TYPEID = -524548743;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final String path;
	public final int costitemid;
	public final float costtime;
	public final float collectradius;
	public final boolean showmineralname;
	public final boolean disappear;
	public final float disappeartime;
	public final float refreshtime;
	public final float protecttime;
	public final int dropid;
	public final boolean canbreak;
	public final int requiretaskid;
	public final boolean showmineralbar;
	public Mineral(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.path = fs.getString();
		this.costitemid = fs.getInt();
		this.costtime = fs.getFloat();
		this.collectradius = fs.getFloat();
		this.showmineralname = fs.getBool();
		this.disappear = fs.getBool();
		this.disappeartime = fs.getFloat();
		this.refreshtime = fs.getFloat();
		this.protecttime = fs.getFloat();
		this.dropid = fs.getInt();
		this.canbreak = fs.getBool();
		this.requiretaskid = fs.getInt();
		this.showmineralbar = fs.getBool();
	}
}
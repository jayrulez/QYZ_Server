package cfg.arena;
public final class Robot extends cfg.CfgObject {
	public final static int TYPEID = -898905575;
	final public int getTypeId() { return TYPEID; }
	public final int rank;
	public final String name;
	public final int gender;
	public final int profession;
	public final int level;
	public final int viplevel;
	public final int combatpower;
	public Robot(cfg.DataStream fs) {
		this.rank = fs.getInt();
		this.name = fs.getString();
		this.gender = fs.getInt();
		this.profession = fs.getInt();
		this.level = fs.getInt();
		this.viplevel = fs.getInt();
		this.combatpower = fs.getInt();
	}
}
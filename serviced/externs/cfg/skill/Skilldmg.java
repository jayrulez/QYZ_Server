package cfg.skill;
public final class Skilldmg extends cfg.CfgObject {
	public final static int TYPEID = 1707681524;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final int nextskillid;
	public final String actionname;
	public final int actiontype;
	public final String icon;
	public final String introduction;
	public final boolean isnormal;
	public final float cd;
	public final float cost;
	public final float costperlvl;
	public final java.util.List<cfg.skill.HitPointAction> hitpoints = new java.util.ArrayList<>();
	public Skilldmg(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.nextskillid = fs.getInt();
		this.actionname = fs.getString();
		this.actiontype = fs.getInt();
		this.icon = fs.getString();
		this.introduction = fs.getString();
		this.isnormal = fs.getBool();
		this.cd = fs.getFloat();
		this.cost = fs.getFloat();
		this.costperlvl = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.hitpoints.add((cfg.skill.HitPointAction)fs.getObject(fs.getString()));
		}
	}
}
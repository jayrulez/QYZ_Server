package cfg.skill;
public final class PassiveSkill extends cfg.CfgObject {
	public final static int TYPEID = -1595123247;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final String icon;
	public final String introduction;
	public final int passivebuffid;
	public PassiveSkill(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.icon = fs.getString();
		this.introduction = fs.getString();
		this.passivebuffid = fs.getInt();
	}
}
package cfg.active;
public final class Activebonus extends cfg.CfgObject {
	public final static int TYPEID = -1405982821;
	final public int getTypeId() { return TYPEID; }
	public final int grade;
	public final String icon;
	public final cfg.cmd.action.MultiBonus award;
	public Activebonus(cfg.DataStream fs) {
		this.grade = fs.getInt();
		this.icon = fs.getString();
		this.award = new cfg.cmd.action.MultiBonus(fs);
	}
}
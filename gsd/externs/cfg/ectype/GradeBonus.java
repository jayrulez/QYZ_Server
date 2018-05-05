package cfg.ectype;
public final class GradeBonus extends cfg.CfgObject {
	public final static int TYPEID = 1675490932;
	final public int getTypeId() { return TYPEID; }
	public final int grade;
	public final cfg.cmd.action.MultiBonus bonus;
	public GradeBonus(cfg.DataStream fs) {
		this.grade = fs.getInt();
		this.bonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
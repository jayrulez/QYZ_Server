package cfg.family;
public final class SpecBonus extends cfg.CfgObject {
	public final static int TYPEID = -1700106428;
	final public int getTypeId() { return TYPEID; }
	public final int requiretaskamount;
	public final cfg.cmd.action.MultiBonus bonus;
	public SpecBonus(cfg.DataStream fs) {
		this.requiretaskamount = fs.getInt();
		this.bonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
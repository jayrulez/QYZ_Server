package cfg.ectype;
public final class FamilyWarAward extends cfg.CfgObject {
	public final static int TYPEID = 1590742469;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final cfg.cmd.action.MultiBonus award;
	public FamilyWarAward(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.award = new cfg.cmd.action.MultiBonus(fs);
	}
}
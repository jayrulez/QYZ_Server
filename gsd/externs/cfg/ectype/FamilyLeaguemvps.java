package cfg.ectype;
public final class FamilyLeaguemvps extends cfg.CfgObject {
	public final static int TYPEID = -1329342069;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.cmd.action.MultiBonus bonus;
	public FamilyLeaguemvps(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.bonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
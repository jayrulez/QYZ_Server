package cfg.ectype;
public final class FamilyLeagueAward1 extends cfg.CfgObject {
	public final static int TYPEID = 1143322099;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final cfg.cmd.action.MultiBonus award;
	public FamilyLeagueAward1(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.award = new cfg.cmd.action.MultiBonus(fs);
	}
}
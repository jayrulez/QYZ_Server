package cfg.ectype;
public final class FamilyLeagueAward2 extends cfg.CfgObject {
	public final static int TYPEID = 1143322100;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.cmd.action.MultiBonus award1;
	public final cfg.cmd.action.MultiBonus award2;
	public FamilyLeagueAward2(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.award1 = new cfg.cmd.action.MultiBonus(fs);
		this.award2 = new cfg.cmd.action.MultiBonus(fs);
	}
}
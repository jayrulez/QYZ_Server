package cfg.ectype;
public final class FamilyTeamLevelReward extends cfg.CfgObject {
	public final static int TYPEID = -123477562;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final cfg.cmd.action.JingYan bonus;
	public FamilyTeamLevelReward(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.bonus = new cfg.cmd.action.JingYan(fs);
	}
}
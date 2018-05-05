package cfg.ectype;
public final class TeamFightLevelGroup extends cfg.CfgObject {
	public final static int TYPEID = 1644654338;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final cfg.cmd.action.MultiBonus winbonus;
	public final cfg.cmd.action.MultiBonus losebonus;
	public final cfg.cmd.action.MultiBonus drawbonus;
	public TeamFightLevelGroup(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.winbonus = new cfg.cmd.action.MultiBonus(fs);
		this.losebonus = new cfg.cmd.action.MultiBonus(fs);
		this.drawbonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
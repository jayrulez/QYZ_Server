package cfg.ectype;
public final class ExpMonsterBonus extends cfg.CfgObject {
	public final static int TYPEID = 775304886;
	final public int getTypeId() { return TYPEID; }
	public final int killnum;
	public final String bonusdes;
	public final cfg.cmd.action.MultiBonus killbonus;
	public ExpMonsterBonus(cfg.DataStream fs) {
		this.killnum = fs.getInt();
		this.bonusdes = fs.getString();
		this.killbonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
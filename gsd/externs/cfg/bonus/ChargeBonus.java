package cfg.bonus;
public final class ChargeBonus extends cfg.CfgObject {
	public final static int TYPEID = -422627502;
	final public int getTypeId() { return TYPEID; }
	public static final int chargeNPC = 23000020;
	public final int bonusid;
	public final int bonustype;
	public final int requirevalue;
	public final cfg.cmd.action.MultiBonus bonuslist;
	public ChargeBonus(cfg.DataStream fs) {
		this.bonusid = fs.getInt();
		this.bonustype = fs.getInt();
		this.requirevalue = fs.getInt();
		this.bonuslist = new cfg.cmd.action.MultiBonus(fs);
	}
}
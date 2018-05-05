package cfg.pay;
public final class ActiveChargeBonus extends cfg.CfgObject {
	public final static int TYPEID = 107275509;
	final public int getTypeId() { return TYPEID; }
	public final int day;
	public final cfg.cmd.action.MultiBonus bonus;
	public ActiveChargeBonus(cfg.DataStream fs) {
		this.day = fs.getInt();
		this.bonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
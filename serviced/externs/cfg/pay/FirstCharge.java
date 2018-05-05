package cfg.pay;
public final class FirstCharge extends cfg.CfgObject {
	public final static int TYPEID = -464812140;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.Bonus bonus;
	public final int rmbtojifen;
	public FirstCharge(cfg.DataStream fs) {
		this.bonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
		this.rmbtojifen = fs.getInt();
	}
}
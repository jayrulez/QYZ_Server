package cfg.family;
public final class FamilyShop extends cfg.CfgObject {
	public final static int TYPEID = 1736507994;
	final public int getTypeId() { return TYPEID; }
	public final int shoplevel;
	public final cfg.cmd.condition.FamilyMoney shoprequirecapital;
	public FamilyShop(cfg.DataStream fs) {
		this.shoplevel = fs.getInt();
		this.shoprequirecapital = new cfg.cmd.condition.FamilyMoney(fs);
	}
}
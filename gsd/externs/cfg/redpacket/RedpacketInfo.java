package cfg.redpacket;
public final class RedpacketInfo extends cfg.CfgObject {
	public final static int TYPEID = 1417398312;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int needitemid;
	public final int currencytype;
	public final int amount;
	public final int usernumber;
	public final boolean isbrocast;
	public final cfg.cmd.action.MultiBonus returnbonus;
	public RedpacketInfo(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.needitemid = fs.getInt();
		this.currencytype = fs.getInt();
		this.amount = fs.getInt();
		this.usernumber = fs.getInt();
		this.isbrocast = fs.getBool();
		this.returnbonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
package cfg.friend;
public final class MarrigePackage extends cfg.CfgObject {
	public final static int TYPEID = -1626567559;
	final public int getTypeId() { return TYPEID; }
	public final int marrigepackid;
	public final cfg.cmd.condition.FixCurrency marrigepackprice;
	public MarrigePackage(cfg.DataStream fs) {
		this.marrigepackid = fs.getInt();
		this.marrigepackprice = (cfg.cmd.condition.FixCurrency)fs.getObject(fs.getString());
	}
}
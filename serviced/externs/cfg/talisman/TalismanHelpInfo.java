package cfg.talisman;
public final class TalismanHelpInfo extends cfg.CfgObject {
	public final static int TYPEID = 2062949907;
	final public int getTypeId() { return TYPEID; }
	public final String indexname;
	public final String helpinfo;
	public TalismanHelpInfo(cfg.DataStream fs) {
		this.indexname = fs.getString();
		this.helpinfo = fs.getString();
	}
}
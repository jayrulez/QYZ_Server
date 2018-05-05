package cfg.exchange;
public final class ExchangeIndex extends cfg.CfgObject {
	public final static int TYPEID = -1489575794;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int name;
	public final String displayname;
	public final int parentindex;
	public ExchangeIndex(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getInt();
		this.displayname = fs.getString();
		this.parentindex = fs.getInt();
	}
}
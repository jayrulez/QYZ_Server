package cfg.role;
public final class PlatIdToDetail extends cfg.CfgObject {
	public final static int TYPEID = -1584251516;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String shortname;
	public final String detail;
	public final String android;
	public final String ios;
	public PlatIdToDetail(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.shortname = fs.getString();
		this.detail = fs.getString();
		this.android = fs.getString();
		this.ios = fs.getString();
	}
}
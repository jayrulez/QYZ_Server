package cfg.broadcast;
public final class Notice extends cfg.CfgObject {
	public final static int TYPEID = -1505243889;
	final public int getTypeId() { return TYPEID; }
	public final String name;
	public final String content;
	public final String date;
	public final boolean isnew;
	public Notice(cfg.DataStream fs) {
		this.name = fs.getString();
		this.content = fs.getString();
		this.date = fs.getString();
		this.isnew = fs.getBool();
	}
}
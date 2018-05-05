package cfg.modulehelpinfo;
public final class HelpInfo extends cfg.CfgObject {
	public final static int TYPEID = -1155008808;
	final public int getTypeId() { return TYPEID; }
	public final String title;
	public final String content;
	public HelpInfo(cfg.DataStream fs) {
		this.title = fs.getString();
		this.content = fs.getString();
	}
}
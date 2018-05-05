package cfg.item;
public final class SourceInfo extends cfg.CfgObject {
	public final static int TYPEID = 1628092346;
	final public int getTypeId() { return TYPEID; }
	public final String desc;
	public final String dlgname;
	public final int tabindex1;
	public final int tabindex2;
	public final int tabindex3;
	public SourceInfo(cfg.DataStream fs) {
		this.desc = fs.getString();
		this.dlgname = fs.getString();
		this.tabindex1 = fs.getInt();
		this.tabindex2 = fs.getInt();
		this.tabindex3 = fs.getInt();
	}
}
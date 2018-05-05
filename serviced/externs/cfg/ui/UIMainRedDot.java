package cfg.ui;
public final class UIMainRedDot extends cfg.CfgObject {
	public final static int TYPEID = -74939563;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int functionname;
	public final int opentype;
	public final int conid;
	public final int dottype;
	public final String desc;
	public UIMainRedDot(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.functionname = fs.getInt();
		this.opentype = fs.getInt();
		this.conid = fs.getInt();
		this.dottype = fs.getInt();
		this.desc = fs.getString();
	}
}
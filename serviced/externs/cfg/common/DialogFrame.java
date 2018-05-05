package cfg.common;
public final class DialogFrame extends cfg.CfgObject {
	public final static int TYPEID = -1378707668;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final String frame;
	public DialogFrame(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.frame = fs.getString();
	}
}
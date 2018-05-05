package cfg.ui;
public final class moduleunlockcond extends cfg.CfgObject {
	public final static int TYPEID = 537728866;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int openlevel;
	public final int opentaskid;
	public final String desc;
	public moduleunlockcond(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.openlevel = fs.getInt();
		this.opentaskid = fs.getInt();
		this.desc = fs.getString();
	}
}
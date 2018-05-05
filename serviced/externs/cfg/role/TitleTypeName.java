package cfg.role;
public final class TitleTypeName extends cfg.CfgObject {
	public final static int TYPEID = 854962575;
	final public int getTypeId() { return TYPEID; }
	public final int typeid;
	public final String typename;
	public TitleTypeName(cfg.DataStream fs) {
		this.typeid = fs.getInt();
		this.typename = fs.getString();
	}
}
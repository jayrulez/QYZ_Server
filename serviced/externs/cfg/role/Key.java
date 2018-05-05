package cfg.role;
public final class Key extends cfg.CfgObject {
	public final static int TYPEID = 716506897;
	final public int getTypeId() { return TYPEID; }
	public final String id;
	public final String password;
	public Key(cfg.DataStream fs) {
		this.id = fs.getString();
		this.password = fs.getString();
	}
}
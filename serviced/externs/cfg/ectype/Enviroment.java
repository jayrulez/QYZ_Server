package cfg.ectype;
public final class Enviroment extends cfg.CfgObject {
	public final static int TYPEID = -791067357;
	final public int getTypeId() { return TYPEID; }
	public final int name;
	public final int value;
	public Enviroment(cfg.DataStream fs) {
		this.name = fs.getInt();
		this.value = fs.getInt();
	}
}
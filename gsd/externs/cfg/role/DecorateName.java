package cfg.role;
public final class DecorateName extends cfg.CfgObject {
	public final static int TYPEID = 2056063686;
	final public int getTypeId() { return TYPEID; }
	public final String name;
	public final int position;
	public final int weight;
	public DecorateName(cfg.DataStream fs) {
		this.name = fs.getString();
		this.position = fs.getInt();
		this.weight = fs.getInt();
	}
}
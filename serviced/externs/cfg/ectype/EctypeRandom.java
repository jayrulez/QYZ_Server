package cfg.ectype;
public final class EctypeRandom extends cfg.CfgObject {
	public final static int TYPEID = -428812121;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int itemid;
	public final int weight;
	public final int color;
	public final String refstring;
	public EctypeRandom(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.itemid = fs.getInt();
		this.weight = fs.getInt();
		this.color = fs.getInt();
		this.refstring = fs.getString();
	}
}
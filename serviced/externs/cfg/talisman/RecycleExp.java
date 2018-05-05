package cfg.talisman;
public final class RecycleExp extends cfg.CfgObject {
	public final static int TYPEID = -962734979;
	final public int getTypeId() { return TYPEID; }
	public final int itemkey;
	public RecycleExp(cfg.DataStream fs) {
		this.itemkey = fs.getInt();
	}
}
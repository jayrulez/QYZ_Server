package cfg.task;
public final class ItemInfo extends cfg.CfgObject {
	public final static int TYPEID = -1201711936;
	final public int getTypeId() { return TYPEID; }
	public final int itemid;
	public final int itemcount;
	public final int professionbelongs;
	public ItemInfo(cfg.DataStream fs) {
		this.itemid = fs.getInt();
		this.itemcount = fs.getInt();
		this.professionbelongs = fs.getInt();
	}
}
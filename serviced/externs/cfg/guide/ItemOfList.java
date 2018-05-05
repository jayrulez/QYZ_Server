package cfg.guide;
public final class ItemOfList extends cfg.guide.LockObject {
	public final static int TYPEID = 1834942116;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> itemid = new java.util.ArrayList<>();
	public ItemOfList(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.itemid.add(fs.getInt());
		}
	}
}
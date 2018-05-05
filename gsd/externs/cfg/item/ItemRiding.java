package cfg.item;
public final class ItemRiding extends cfg.item.ItemBasic {
	public final static int TYPEID = 405975289;
	final public int getTypeId() { return TYPEID; }
	public final int ridingid;
	public final int effectiveime;
	public ItemRiding(cfg.DataStream fs) {
		super(fs);
		this.ridingid = fs.getInt();
		this.effectiveime = fs.getInt();
	}
}
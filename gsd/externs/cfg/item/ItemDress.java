package cfg.item;
public final class ItemDress extends cfg.item.ItemBasic {
	public final static int TYPEID = 277530739;
	final public int getTypeId() { return TYPEID; }
	public final int dressid;
	public final int effectiveime;
	public ItemDress(cfg.DataStream fs) {
		super(fs);
		this.dressid = fs.getInt();
		this.effectiveime = fs.getInt();
	}
}
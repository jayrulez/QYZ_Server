package cfg.item;
public final class ItemEnhance extends cfg.item.ItemBasic {
	public final static int TYPEID = 1194287594;
	final public int getTypeId() { return TYPEID; }
	public final String sprite;
	public ItemEnhance(cfg.DataStream fs) {
		super(fs);
		this.sprite = fs.getString();
	}
}
package cfg.item;
public final class ItemCurrency extends cfg.item.ItemBasic {
	public final static int TYPEID = 1396848725;
	final public int getTypeId() { return TYPEID; }
	public final int currencytype;
	public final int amount;
	public final String sprite;
	public ItemCurrency(cfg.DataStream fs) {
		super(fs);
		this.currencytype = fs.getInt();
		this.amount = fs.getInt();
		this.sprite = fs.getString();
	}
}
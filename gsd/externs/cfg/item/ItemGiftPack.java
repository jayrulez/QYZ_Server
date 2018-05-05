package cfg.item;
public final class ItemGiftPack extends cfg.item.ItemBasic {
	public final static int TYPEID = 1670672301;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.MultiBonus itempacklist;
	public ItemGiftPack(cfg.DataStream fs) {
		super(fs);
		this.itempacklist = new cfg.cmd.action.MultiBonus(fs);
	}
}
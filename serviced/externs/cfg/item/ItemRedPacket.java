package cfg.item;
public final class ItemRedPacket extends cfg.item.ItemBasic {
	public final static int TYPEID = 672874677;
	final public int getTypeId() { return TYPEID; }
	public ItemRedPacket(cfg.DataStream fs) {
		super(fs);
	}
}
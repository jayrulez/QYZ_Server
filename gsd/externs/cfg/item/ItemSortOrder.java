package cfg.item;
public final class ItemSortOrder extends cfg.CfgObject {
	public final static int TYPEID = -331729780;
	final public int getTypeId() { return TYPEID; }
	public static final String ITEM_SORT_ORDER = "level,quality,number";
	public static final String ITEM_SORT_RULE = "desc,desc,asc";
	public ItemSortOrder(cfg.DataStream fs) {
	}
}
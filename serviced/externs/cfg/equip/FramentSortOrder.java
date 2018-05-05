package cfg.equip;
public final class FramentSortOrder extends cfg.CfgObject {
	public final static int TYPEID = 1932772485;
	final public int getTypeId() { return TYPEID; }
	public static final String ITEM_SORT_ORDER = "profession,quality,level,eitemtype";
	public static final String ITEM_SORT_RULE = "desc,desc,desc,desc";
	public FramentSortOrder(cfg.DataStream fs) {
	}
}
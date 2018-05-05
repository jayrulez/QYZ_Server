package cfg.equip;
public final class EquipSortOrder extends cfg.CfgObject {
	public final static int TYPEID = 1759705832;
	final public int getTypeId() { return TYPEID; }
	public static final String EQUIP_SORT_ORDER = "profession,quality,level,type";
	public static final String EQUIP_SORT_RULE = "desc,desc,desc,desc";
	public EquipSortOrder(cfg.DataStream fs) {
	}
}
package cfg.exchange;
public final class Const extends cfg.CfgObject {
	public final static int TYPEID = -1312121214;
	final public int getTypeId() { return TYPEID; }
	public static final int MAX_EXCHANGE_ITEM_NUM = 50;
	public static final int MAX_EXCHANGE_SAVE_LOG_NUM = 200;
	public static final int MAX_EXCHANGE_SHOW_LOG_NUM = 50;
	public static final int BUY_COST_CURRENTCY_TYPE = 10200002;
	public static final int NUM_OF_PAGE = 20;
	public static final int DEFAULT_SORT_TYPE = 0;
	public static final int DEFAULT_SORT_ORDER = 1;
	public static final int OPEN_LEVEL = 0;
	public static final float EXCHANGE_TAX = 0.05f;
	public static final int EXCHANGE_UNSHELVE_TIME = 172800;
	public static final int UNSHELVE_MAILID = 17;
	public Const(cfg.DataStream fs) {
	}
}
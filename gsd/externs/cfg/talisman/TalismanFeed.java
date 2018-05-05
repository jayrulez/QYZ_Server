package cfg.talisman;
public final class TalismanFeed extends cfg.CfgObject {
	public final static int TYPEID = 1391052322;
	final public int getTypeId() { return TYPEID; }
	public static final int WASH_COST = 0;
	public static final int REQUIRE_ITEM = 10400012;
	public static final int REQUIRE_ITEM_NUM = 1;
	public static final int CHANGE_PROPERTY_COST = 100;
	public static final int FREE_CHANGE_LUCK_TIMES = 2;
	public static final int WASH_LUCK_COST = 10;
	public static final int BEST_LUCK_COST = 30;
	public static final int BEST_LUCK_ID = 1;
	public static final int DEFAULT_LUCK_ID = 3;
	public static final int DEFAULT_WUXING = 2;
	public static final int WUXING_OPEN_LEVEL = 50;
	public final int id;
	public final int name;
	public final String luckname;
	public final int lowerbound;
	public final int upperbound;
	public final float criticallowerbound;
	public final float criticalupperbound;
	public final float criticalrate;
	public final int maxusetime;
	public final float probability;
	public final String decribe;
	public TalismanFeed(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getInt();
		this.luckname = fs.getString();
		this.lowerbound = fs.getInt();
		this.upperbound = fs.getInt();
		this.criticallowerbound = fs.getFloat();
		this.criticalupperbound = fs.getFloat();
		this.criticalrate = fs.getFloat();
		this.maxusetime = fs.getInt();
		this.probability = fs.getFloat();
		this.decribe = fs.getString();
	}
}
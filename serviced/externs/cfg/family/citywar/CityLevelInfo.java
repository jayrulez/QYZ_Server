package cfg.family.citywar;
public final class CityLevelInfo extends cfg.CfgObject {
	public final static int TYPEID = 241509558;
	final public int getTypeId() { return TYPEID; }
	public static final int MONEY_TYPE = 10200008;
	public final int level;
	public final int minmoney;
	public final int maxmoney;
	public final int score;
	public final int luckybonusweight;
	public CityLevelInfo(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.minmoney = fs.getInt();
		this.maxmoney = fs.getInt();
		this.score = fs.getInt();
		this.luckybonusweight = fs.getInt();
	}
}
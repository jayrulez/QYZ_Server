package cfg.timegift;
public final class DayBonusInfo extends cfg.CfgObject {
	public final static int TYPEID = 395782168;
	final public int getTypeId() { return TYPEID; }
	public final int day;
	public final String showday;
	public final String para;
	public final int parabig;
	public final java.util.List<cfg.timegift.DailyBonus> dailybonus = new java.util.ArrayList<>();
	public DayBonusInfo(cfg.DataStream fs) {
		this.day = fs.getInt();
		this.showday = fs.getString();
		this.para = fs.getString();
		this.parabig = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.dailybonus.add(new cfg.timegift.DailyBonus(fs));
		}
	}
}
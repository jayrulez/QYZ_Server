package cfg.bonus;
public final class OnlineTimeBonus2 extends cfg.CfgObject {
	public final static int TYPEID = 1321754284;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String desc;
	public final String name;
	public final cfg.common.DateTimeRange datetime;
	public final String background;
	public final String logindaydecs;
	public final java.util.List<cfg.bonus.DayBonusInfo> daybonus = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.bonus.DayBonusInfo> daybonus_day= new java.util.HashMap<>();
	public OnlineTimeBonus2(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.desc = fs.getString();
		this.name = fs.getString();
		this.datetime = new cfg.common.DateTimeRange(fs);
		this.background = fs.getString();
		this.logindaydecs = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.bonus.DayBonusInfo _v = new cfg.bonus.DayBonusInfo(fs);
			this.daybonus.add(_v);
			this.daybonus_day.put(_v.day, _v);
		}
	}
}
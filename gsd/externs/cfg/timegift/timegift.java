package cfg.timegift;
public final class timegift extends cfg.CfgObject {
	public final static int TYPEID = -140697660;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final String desc;
	public final cfg.common.DateTimeRange datetime;
	public final String background;
	public final String logindaydecs;
	public final java.util.List<cfg.timegift.DayBonusInfo> daybonus = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.timegift.DayBonusInfo> daybonus_day= new java.util.HashMap<>();
	public timegift(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.desc = fs.getString();
		this.datetime = new cfg.common.DateTimeRange(fs);
		this.background = fs.getString();
		this.logindaydecs = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.timegift.DayBonusInfo _v = new cfg.timegift.DayBonusInfo(fs);
			this.daybonus.add(_v);
			this.daybonus_day.put(_v.day, _v);
		}
	}
}
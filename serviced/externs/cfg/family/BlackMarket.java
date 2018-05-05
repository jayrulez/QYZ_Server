package cfg.family;
public final class BlackMarket extends cfg.CfgObject {
	public final static int TYPEID = -762943685;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.common.DayTimeRange> opentime = new java.util.ArrayList<>();
	public BlackMarket(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.opentime.add(new cfg.common.DayTimeRange(fs));
		}
	}
}
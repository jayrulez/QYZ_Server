package cfg.active;
public final class FindBackFreeTime extends cfg.CfgObject {
	public final static int TYPEID = 1366420535;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.common.DateTimeRange> time = new java.util.ArrayList<>();
	public FindBackFreeTime(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.time.add(new cfg.common.DateTimeRange(fs));
		}
	}
}
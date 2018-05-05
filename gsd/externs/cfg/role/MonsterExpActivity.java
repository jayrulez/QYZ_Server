package cfg.role;
public final class MonsterExpActivity extends cfg.CfgObject {
	public final static int TYPEID = -890034912;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.common.DateTimeRange openrange;
	public final cfg.common.DateTime addexpendtime;
	public final java.util.Map<Integer, Long> dailyaddexps = new java.util.HashMap<>();
	public MonsterExpActivity(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.openrange = new cfg.common.DateTimeRange(fs);
		this.addexpendtime = new cfg.common.DateTime(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.dailyaddexps.put(_k, fs.getLong());
		}
	}
}
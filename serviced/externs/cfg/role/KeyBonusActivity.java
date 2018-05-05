package cfg.role;
public final class KeyBonusActivity extends cfg.CfgObject {
	public final static int TYPEID = 1664144573;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.common.DateTimeRange openrange;
	public final int rolemaxobtainnum;
	public final float probability;
	public final java.util.Map<Integer, cfg.role.KeySet> keysbyserverid = new java.util.HashMap<>();
	public KeyBonusActivity(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.openrange = new cfg.common.DateTimeRange(fs);
		this.rolemaxobtainnum = fs.getInt();
		this.probability = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.keysbyserverid.put(_k, new cfg.role.KeySet(fs));
		}
	}
}
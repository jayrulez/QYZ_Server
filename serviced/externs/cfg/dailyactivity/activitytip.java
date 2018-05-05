package cfg.dailyactivity;
public final class activitytip extends cfg.CfgObject {
	public final static int TYPEID = 490806620;
	final public int getTypeId() { return TYPEID; }
	public final java.util.Map<Integer, String> activitytipmap = new java.util.HashMap<>();
	public final int tipshowduration;
	public activitytip(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.activitytipmap.put(_k, fs.getString());
		}
		this.tipshowduration = fs.getInt();
	}
}
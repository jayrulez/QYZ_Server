package cfg.herotask;
public final class HeroLimit extends cfg.CfgObject {
	public final static int TYPEID = -1780097316;
	final public int getTypeId() { return TYPEID; }
	public final int ectypeid;
	public final int mainregionid;
	public final int requirekill;
	public final int interval;
	public final java.util.List<cfg.herotask.HeroLimitInfo> monsterinfo = new java.util.ArrayList<>();
	public HeroLimit(cfg.DataStream fs) {
		this.ectypeid = fs.getInt();
		this.mainregionid = fs.getInt();
		this.requirekill = fs.getInt();
		this.interval = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterinfo.add(new cfg.herotask.HeroLimitInfo(fs));
		}
	}
}
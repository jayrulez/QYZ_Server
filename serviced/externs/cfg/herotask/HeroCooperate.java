package cfg.herotask;
public final class HeroCooperate extends cfg.CfgObject {
	public final static int TYPEID = 232949305;
	final public int getTypeId() { return TYPEID; }
	public final int ectypeid;
	public final int mainregionid;
	public final int interval;
	public final java.util.List<cfg.herotask.HeroCooperateInfo> monsterinfo = new java.util.ArrayList<>();
	public HeroCooperate(cfg.DataStream fs) {
		this.ectypeid = fs.getInt();
		this.mainregionid = fs.getInt();
		this.interval = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterinfo.add(new cfg.herotask.HeroCooperateInfo(fs));
		}
	}
}
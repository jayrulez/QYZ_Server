package cfg.herotask;
public final class HeroCommon extends cfg.CfgObject {
	public final static int TYPEID = 399440618;
	final public int getTypeId() { return TYPEID; }
	public final int ectypeid;
	public final int mainregionid;
	public final java.util.List<cfg.herotask.HeroCommonInfo> monsterlist = new java.util.ArrayList<>();
	public HeroCommon(cfg.DataStream fs) {
		this.ectypeid = fs.getInt();
		this.mainregionid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterlist.add(new cfg.herotask.HeroCommonInfo(fs));
		}
	}
}
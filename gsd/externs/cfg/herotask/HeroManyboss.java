package cfg.herotask;
public final class HeroManyboss extends cfg.CfgObject {
	public final static int TYPEID = -1937209461;
	final public int getTypeId() { return TYPEID; }
	public final int ectypeid;
	public final int mainregionid;
	public final int buff;
	public final int debuff;
	public final java.util.List<cfg.herotask.HeroManyBossInfo> monsterinfo = new java.util.ArrayList<>();
	public HeroManyboss(cfg.DataStream fs) {
		this.ectypeid = fs.getInt();
		this.mainregionid = fs.getInt();
		this.buff = fs.getInt();
		this.debuff = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterinfo.add(new cfg.herotask.HeroManyBossInfo(fs));
		}
	}
}
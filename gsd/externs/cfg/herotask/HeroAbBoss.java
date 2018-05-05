package cfg.herotask;
public final class HeroAbBoss extends cfg.CfgObject {
	public final static int TYPEID = 328897581;
	final public int getTypeId() { return TYPEID; }
	public final int ectypeid;
	public final int mainregionid;
	public final int buffid;
	public final java.util.List<cfg.herotask.HeroAbBossInfo> monsterinfo = new java.util.ArrayList<>();
	public HeroAbBoss(cfg.DataStream fs) {
		this.ectypeid = fs.getInt();
		this.mainregionid = fs.getInt();
		this.buffid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterinfo.add(new cfg.herotask.HeroAbBossInfo(fs));
		}
	}
}
package cfg.herotask;
public final class HeroAbBossInfo extends cfg.CfgObject {
	public final static int TYPEID = -556953989;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final java.util.List<cfg.herotask.HeroBossInfo> bosslist = new java.util.ArrayList<>();
	public HeroAbBossInfo(cfg.DataStream fs) {
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bosslist.add(new cfg.herotask.HeroBossInfo(fs));
		}
	}
}
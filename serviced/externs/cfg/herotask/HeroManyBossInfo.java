package cfg.herotask;
public final class HeroManyBossInfo extends cfg.CfgObject {
	public final static int TYPEID = -2104424711;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final java.util.List<cfg.herotask.HeroBossInfo> bosslist = new java.util.ArrayList<>();
	public HeroManyBossInfo(cfg.DataStream fs) {
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bosslist.add(new cfg.herotask.HeroBossInfo(fs));
		}
	}
}
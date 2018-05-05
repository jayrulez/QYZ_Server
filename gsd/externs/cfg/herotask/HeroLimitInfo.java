package cfg.herotask;
public final class HeroLimitInfo extends cfg.CfgObject {
	public final static int TYPEID = 1611000234;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final java.util.List<cfg.herotask.MonsterInfo> monsterlist = new java.util.ArrayList<>();
	public HeroLimitInfo(cfg.DataStream fs) {
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterlist.add(new cfg.herotask.MonsterInfo(fs));
		}
	}
}
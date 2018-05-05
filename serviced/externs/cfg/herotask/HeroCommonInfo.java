package cfg.herotask;
public final class HeroCommonInfo extends cfg.CfgObject {
	public final static int TYPEID = 1355173560;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final java.util.List<cfg.herotask.MonsterInfo> monsterlist = new java.util.ArrayList<>();
	public HeroCommonInfo(cfg.DataStream fs) {
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterlist.add(new cfg.herotask.MonsterInfo(fs));
		}
	}
}
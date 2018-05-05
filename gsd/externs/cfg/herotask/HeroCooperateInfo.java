package cfg.herotask;
public final class HeroCooperateInfo extends cfg.CfgObject {
	public final static int TYPEID = -1334470009;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final java.util.List<cfg.herotask.HeroBossInfo> bosslist = new java.util.ArrayList<>();
	public final java.util.List<cfg.herotask.MonsterInfo> monsterlist = new java.util.ArrayList<>();
	public HeroCooperateInfo(cfg.DataStream fs) {
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bosslist.add(new cfg.herotask.HeroBossInfo(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterlist.add(new cfg.herotask.MonsterInfo(fs));
		}
	}
}
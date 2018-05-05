package cfg.family.citywar;
public final class LevelMonsters extends cfg.CfgObject {
	public final static int TYPEID = -818015476;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final java.util.List<Integer> monsterids = new java.util.ArrayList<>();
	public LevelMonsters(cfg.DataStream fs) {
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterids.add(fs.getInt());
		}
	}
}
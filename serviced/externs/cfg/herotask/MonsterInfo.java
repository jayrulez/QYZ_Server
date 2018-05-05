package cfg.herotask;
public final class MonsterInfo extends cfg.CfgObject {
	public final static int TYPEID = -1290782749;
	final public int getTypeId() { return TYPEID; }
	public final int region;
	public final java.util.Map<Integer, Integer> monster = new java.util.HashMap<>();
	public MonsterInfo(cfg.DataStream fs) {
		this.region = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.monster.put(_k, fs.getInt());
		}
	}
}
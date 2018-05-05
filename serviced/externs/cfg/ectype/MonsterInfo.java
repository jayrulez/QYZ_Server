package cfg.ectype;
public final class MonsterInfo extends cfg.CfgObject {
	public final static int TYPEID = -1420007108;
	final public int getTypeId() { return TYPEID; }
	public final boolean isboss;
	public final int regionid;
	public final java.util.Map<Integer, Integer> monsters = new java.util.HashMap<>();
	public final cfg.map.PatrolInfo patrol;
	public MonsterInfo(cfg.DataStream fs) {
		this.isboss = fs.getBool();
		this.regionid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.monsters.put(_k, fs.getInt());
		}
		this.patrol = (cfg.map.PatrolInfo)fs.getObject(fs.getString());
	}
}
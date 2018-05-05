package cfg.ectype;
public final class CurrencyMonsterRef extends cfg.CfgObject {
	public final static int TYPEID = -1437734602;
	final public int getTypeId() { return TYPEID; }
	public final java.util.Map<Integer, Integer> monsters = new java.util.HashMap<>();
	public final int regionid;
	public final cfg.map.PatrolInfo patrol;
	public CurrencyMonsterRef(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.monsters.put(_k, fs.getInt());
		}
		this.regionid = fs.getInt();
		this.patrol = (cfg.map.PatrolInfo)fs.getObject(fs.getString());
	}
}
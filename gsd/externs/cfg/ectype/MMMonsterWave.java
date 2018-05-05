package cfg.ectype;
public final class MMMonsterWave extends cfg.CfgObject {
	public final static int TYPEID = 204057831;
	final public int getTypeId() { return TYPEID; }
	public final int regionid;
	public final java.util.Map<Integer, Integer> monsterinfo = new java.util.HashMap<>();
	public MMMonsterWave(cfg.DataStream fs) {
		this.regionid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.monsterinfo.put(_k, fs.getInt());
		}
	}
}
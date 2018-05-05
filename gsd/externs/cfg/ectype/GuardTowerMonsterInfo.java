package cfg.ectype;
public final class GuardTowerMonsterInfo extends cfg.CfgObject {
	public final static int TYPEID = 1947413048;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int regionid;
	public final cfg.cmd.action.MultiBonus clearaward;
	public final java.util.Map<Integer, Integer> monsters = new java.util.HashMap<>();
	public GuardTowerMonsterInfo(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.regionid = fs.getInt();
		this.clearaward = new cfg.cmd.action.MultiBonus(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.monsters.put(_k, fs.getInt());
		}
	}
}
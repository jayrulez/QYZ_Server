package cfg.family;
public final class BossConfig extends cfg.CfgObject {
	public final static int TYPEID = -337162513;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.family.OpenTime> opentime = new java.util.ArrayList<>();
	public final int signtime;
	public final int battletime;
	public final java.util.Map<Integer, Integer> monsterinfo = new java.util.HashMap<>();
	public BossConfig(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.opentime.add(new cfg.family.OpenTime(fs));
		}
		this.signtime = fs.getInt();
		this.battletime = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.monsterinfo.put(_k, fs.getInt());
		}
	}
}
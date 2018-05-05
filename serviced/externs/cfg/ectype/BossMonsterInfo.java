package cfg.ectype;
public final class BossMonsterInfo extends cfg.CfgObject {
	public final static int TYPEID = 106132880;
	final public int getTypeId() { return TYPEID; }
	public final int bosslevel;
	public final java.util.List<Integer> monsterids = new java.util.ArrayList<>();
	public BossMonsterInfo(cfg.DataStream fs) {
		this.bosslevel = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterids.add(fs.getInt());
		}
	}
}
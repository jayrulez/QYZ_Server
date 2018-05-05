package cfg.ectype;
public final class AttackCityRefreshMonster extends cfg.CfgObject {
	public final static int TYPEID = -1697705442;
	final public int getTypeId() { return TYPEID; }
	public final int refreshtime;
	public final String refreshmsg;
	public final java.util.List<cfg.ectype.MonsterRefreshInfo> monsters = new java.util.ArrayList<>();
	public AttackCityRefreshMonster(cfg.DataStream fs) {
		this.refreshtime = fs.getInt();
		this.refreshmsg = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsters.add(new cfg.ectype.MonsterRefreshInfo(fs));
		}
	}
}
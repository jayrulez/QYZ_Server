package cfg.ectype;
public final class MonsterRefreshInfo extends cfg.CfgObject {
	public final static int TYPEID = 1331291611;
	final public int getTypeId() { return TYPEID; }
	public final int regionid;
	public final cfg.map.MonsterSpawn monsters;
	public final cfg.map.PatrolInfo patrol;
	public MonsterRefreshInfo(cfg.DataStream fs) {
		this.regionid = fs.getInt();
		this.monsters = new cfg.map.MonsterSpawn(fs);
		this.patrol = (cfg.map.PatrolInfo)fs.getObject(fs.getString());
	}
}
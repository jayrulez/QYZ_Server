package cfg.ectype;
public final class SpeedMonsterRefInfo extends cfg.CfgObject {
	public final static int TYPEID = -1306858398;
	final public int getTypeId() { return TYPEID; }
	public final cfg.map.MonsterSpawn monsters;
	public final int regionid;
	public final int grade;
	public SpeedMonsterRefInfo(cfg.DataStream fs) {
		this.monsters = new cfg.map.MonsterSpawn(fs);
		this.regionid = fs.getInt();
		this.grade = fs.getInt();
	}
}
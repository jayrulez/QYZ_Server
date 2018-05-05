package cfg.task;
public final class KillMonsterData extends cfg.CfgObject {
	public final static int TYPEID = 1812338983;
	final public int getTypeId() { return TYPEID; }
	public final int monsterid;
	public final int monstercount;
	public final int dropitemid;
	public final int dropitemcount;
	public final float probability;
	public final boolean rolelevellimit;
	public final boolean droponeinteam;
	public KillMonsterData(cfg.DataStream fs) {
		this.monsterid = fs.getInt();
		this.monstercount = fs.getInt();
		this.dropitemid = fs.getInt();
		this.dropitemcount = fs.getInt();
		this.probability = fs.getFloat();
		this.rolelevellimit = fs.getBool();
		this.droponeinteam = fs.getBool();
	}
}
package cfg.map;
public final class MonsterSpawn extends cfg.CfgObject {
	public final static int TYPEID = 143899517;
	final public int getTypeId() { return TYPEID; }
	public final int monsterid;
	public final int count;
	public final float regeneratecd;
	public final int regeneratecount;
	public MonsterSpawn(cfg.DataStream fs) {
		this.monsterid = fs.getInt();
		this.count = fs.getInt();
		this.regeneratecd = fs.getFloat();
		this.regeneratecount = fs.getInt();
	}
}
package cfg.map;
public final class MineralSpawn extends cfg.CfgObject {
	public final static int TYPEID = -1042211219;
	final public int getTypeId() { return TYPEID; }
	public final int mineralid;
	public final int count;
	public final float regeneratecd;
	public final int regeneratecount;
	public MineralSpawn(cfg.DataStream fs) {
		this.mineralid = fs.getInt();
		this.count = fs.getInt();
		this.regeneratecd = fs.getFloat();
		this.regeneratecount = fs.getInt();
	}
}
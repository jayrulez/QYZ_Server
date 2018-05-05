package cfg.role;
public final class WorldExpRate extends cfg.CfgObject {
	public final static int TYPEID = 18671673;
	final public int getTypeId() { return TYPEID; }
	public final int leveldisparity;
	public final float extraexprate;
	public final float monsterexprate;
	public WorldExpRate(cfg.DataStream fs) {
		this.leveldisparity = fs.getInt();
		this.extraexprate = fs.getFloat();
		this.monsterexprate = fs.getFloat();
	}
}
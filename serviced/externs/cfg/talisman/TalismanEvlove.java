package cfg.talisman;
public final class TalismanEvlove extends cfg.CfgObject {
	public final static int TYPEID = 1053745959;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final String name;
	public final float maturerate;
	public final int requireexp;
	public final int levellimit;
	public TalismanEvlove(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.name = fs.getString();
		this.maturerate = fs.getFloat();
		this.requireexp = fs.getInt();
		this.levellimit = fs.getInt();
	}
}
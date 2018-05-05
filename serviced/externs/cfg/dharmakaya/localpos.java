package cfg.dharmakaya;
public final class localpos extends cfg.CfgObject {
	public final static int TYPEID = -1471722970;
	final public int getTypeId() { return TYPEID; }
	public final int x;
	public final int y;
	public localpos(cfg.DataStream fs) {
		this.x = fs.getInt();
		this.y = fs.getInt();
	}
}
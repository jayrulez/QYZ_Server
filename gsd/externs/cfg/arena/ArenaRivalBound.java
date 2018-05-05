package cfg.arena;
public final class ArenaRivalBound extends cfg.CfgObject {
	public final static int TYPEID = 498256234;
	final public int getTypeId() { return TYPEID; }
	public final int low;
	public final int up;
	public ArenaRivalBound(cfg.DataStream fs) {
		this.low = fs.getInt();
		this.up = fs.getInt();
	}
}
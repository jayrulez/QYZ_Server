package cfg.arena;
public final class ArenaMultiList extends cfg.CfgObject {
	public final static int TYPEID = -395518111;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String label;
	public final String tabname;
	public ArenaMultiList(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.label = fs.getString();
		this.tabname = fs.getString();
	}
}
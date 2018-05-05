package cfg.arena;
public final class ArenaShengwang extends cfg.CfgObject {
	public final static int TYPEID = -1626948138;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int minrank;
	public final int addshengwang;
	public ArenaShengwang(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.minrank = fs.getInt();
		this.addshengwang = fs.getInt();
	}
}
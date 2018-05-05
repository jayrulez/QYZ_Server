package cfg.arena;
public final class ArenaMultiPvPBonus extends cfg.CfgObject {
	public final static int TYPEID = 791406712;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int score;
	public ArenaMultiPvPBonus(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.score = fs.getInt();
	}
}
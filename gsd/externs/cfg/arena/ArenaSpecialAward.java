package cfg.arena;
public final class ArenaSpecialAward extends cfg.CfgObject {
	public final static int TYPEID = -1375245574;
	final public int getTypeId() { return TYPEID; }
	public final int times;
	public final cfg.cmd.action.Bonus award;
	public ArenaSpecialAward(cfg.DataStream fs) {
		this.times = fs.getInt();
		this.award = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}
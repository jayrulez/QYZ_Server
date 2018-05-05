package cfg.arena;
public final class ArenaAward extends cfg.CfgObject {
	public final static int TYPEID = 1209346375;
	final public int getTypeId() { return TYPEID; }
	public final int maxlvl;
	public final cfg.cmd.action.Bonus winaward;
	public final cfg.cmd.action.Bonus loseaward;
	public ArenaAward(cfg.DataStream fs) {
		this.maxlvl = fs.getInt();
		this.winaward = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
		this.loseaward = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}
package cfg.role;
public final class LevelStatusInfo extends cfg.CfgObject {
	public final static int TYPEID = 479146006;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final cfg.fight.Attr attr;
	public LevelStatusInfo(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.attr = new cfg.fight.Attr(fs);
	}
}
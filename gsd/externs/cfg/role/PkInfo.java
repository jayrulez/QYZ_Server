package cfg.role;
public final class PkInfo extends cfg.CfgObject {
	public final static int TYPEID = -383126697;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.MinLevel level;
	public PkInfo(cfg.DataStream fs) {
		this.level = new cfg.cmd.condition.MinLevel(fs);
	}
}
package cfg.role;
public final class IndexBonus extends cfg.CfgObject {
	public final static int TYPEID = -710507141;
	final public int getTypeId() { return TYPEID; }
	public final int index;
	public final cfg.cmd.action.MultiBonus bonus;
	public IndexBonus(cfg.DataStream fs) {
		this.index = fs.getInt();
		this.bonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
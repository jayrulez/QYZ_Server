package cfg.cmd.action;
public final class CopyBonus extends cfg.cmd.action.Bonus {
	public final static int TYPEID = 1035684176;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.Bonus bonus;
	public final int num;
	public CopyBonus(cfg.DataStream fs) {
		super(fs);
		this.bonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
		this.num = fs.getInt();
	}
}
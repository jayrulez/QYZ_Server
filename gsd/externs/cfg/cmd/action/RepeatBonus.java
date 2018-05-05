package cfg.cmd.action;
public final class RepeatBonus extends cfg.cmd.action.Bonus {
	public final static int TYPEID = 989421930;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.Bonus bonus;
	public final int num;
	public RepeatBonus(cfg.DataStream fs) {
		super(fs);
		this.bonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
		this.num = fs.getInt();
	}
}
package cfg.cmd.action;
public final class NotBind extends cfg.cmd.action.Bonus {
	public final static int TYPEID = 1582480086;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.Bonus bonus;
	public NotBind(cfg.DataStream fs) {
		super(fs);
		this.bonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}
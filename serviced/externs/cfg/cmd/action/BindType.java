package cfg.cmd.action;
public final class BindType extends cfg.cmd.action.Bonus {
	public final static int TYPEID = 1202310321;
	final public int getTypeId() { return TYPEID; }
	public final int bindtype;
	public BindType(cfg.DataStream fs) {
		super(fs);
		this.bindtype = fs.getInt();
	}
}
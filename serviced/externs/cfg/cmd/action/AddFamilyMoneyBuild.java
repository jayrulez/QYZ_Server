package cfg.cmd.action;
public final class AddFamilyMoneyBuild extends cfg.cmd.action.Action {
	public final static int TYPEID = 1306419961;
	final public int getTypeId() { return TYPEID; }
	public final long money;
	public final int buildv;
	public AddFamilyMoneyBuild(cfg.DataStream fs) {
		super(fs);
		this.money = fs.getLong();
		this.buildv = fs.getInt();
	}
}
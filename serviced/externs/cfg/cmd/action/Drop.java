package cfg.cmd.action;
public final class Drop extends cfg.cmd.action.Bonus {
	public final static int TYPEID = -1416423543;
	final public int getTypeId() { return TYPEID; }
	public final int dropid;
	public Drop(cfg.DataStream fs) {
		super(fs);
		this.dropid = fs.getInt();
	}
}
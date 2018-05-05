package cfg.ectype;
public final class ControllerOperation extends cfg.ectype.Action {
	public final static int TYPEID = -1756769601;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final boolean isopen;
	public ControllerOperation(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.isopen = fs.getBool();
	}
}
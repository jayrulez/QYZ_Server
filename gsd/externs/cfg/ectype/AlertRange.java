package cfg.ectype;
public final class AlertRange extends cfg.ectype.Action {
	public final static int TYPEID = -820350675;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final float range;
	public AlertRange(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.range = fs.getFloat();
	}
}
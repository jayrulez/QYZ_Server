package cfg.ectype;
public final class CirculateTimer extends cfg.ectype.Action {
	public final static int TYPEID = -868238931;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int time;
	public CirculateTimer(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.time = fs.getInt();
	}
}
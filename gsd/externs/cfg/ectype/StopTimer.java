package cfg.ectype;
public final class StopTimer extends cfg.ectype.Action {
	public final static int TYPEID = -962209033;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public StopTimer(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
	}
}
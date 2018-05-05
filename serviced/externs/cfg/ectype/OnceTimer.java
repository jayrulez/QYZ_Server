package cfg.ectype;
public final class OnceTimer extends cfg.ectype.Action {
	public final static int TYPEID = 2025651160;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int time;
	public OnceTimer(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.time = fs.getInt();
	}
}
package cfg.guide;
public final class LockUI extends cfg.guide.GuideEffect {
	public final static int TYPEID = 195486107;
	final public int getTypeId() { return TYPEID; }
	public final int controlobject;
	public final int controlboxcollider;
	public LockUI(cfg.DataStream fs) {
		super(fs);
		this.controlobject = fs.getInt();
		this.controlboxcollider = fs.getInt();
	}
}
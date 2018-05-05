package cfg.guide;
public final class List extends cfg.guide.LockObject {
	public final static int TYPEID = 1001314362;
	final public int getTypeId() { return TYPEID; }
	public final int index;
	public List(cfg.DataStream fs) {
		super(fs);
		this.index = fs.getInt();
	}
}
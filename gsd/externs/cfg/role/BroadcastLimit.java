package cfg.role;
public final class BroadcastLimit extends cfg.CfgObject {
	public final static int TYPEID = -683190392;
	final public int getTypeId() { return TYPEID; }
	public final int maxtotalremain;
	public final int maxsendpersecond;
	public final int addremainpersecond;
	public BroadcastLimit(cfg.DataStream fs) {
		this.maxtotalremain = fs.getInt();
		this.maxsendpersecond = fs.getInt();
		this.addremainpersecond = fs.getInt();
	}
}
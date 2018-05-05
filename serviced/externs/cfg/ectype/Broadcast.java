package cfg.ectype;
public final class Broadcast extends cfg.CfgObject {
	public final static int TYPEID = 94241621;
	final public int getTypeId() { return TYPEID; }
	public final String broadcastdec;
	public Broadcast(cfg.DataStream fs) {
		this.broadcastdec = fs.getString();
	}
}
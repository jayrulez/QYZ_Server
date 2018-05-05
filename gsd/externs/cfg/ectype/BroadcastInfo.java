package cfg.ectype;
public final class BroadcastInfo extends cfg.CfgObject {
	public final static int TYPEID = 901065123;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ectype.Broadcast openbroadcast;
	public final cfg.ectype.Broadcast ongoingbroadcast;
	public final int ongoingterminal;
	public final cfg.ectype.Broadcast endbroadcast;
	public BroadcastInfo(cfg.DataStream fs) {
		this.openbroadcast = new cfg.ectype.Broadcast(fs);
		this.ongoingbroadcast = new cfg.ectype.Broadcast(fs);
		this.ongoingterminal = fs.getInt();
		this.endbroadcast = new cfg.ectype.Broadcast(fs);
	}
}
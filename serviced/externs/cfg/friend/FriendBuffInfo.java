package cfg.friend;
public final class FriendBuffInfo extends cfg.CfgObject {
	public final static int TYPEID = -157931579;
	final public int getTypeId() { return TYPEID; }
	public final float likability;
	public final String buffname;
	public final String introduction;
	public final int buffid;
	public FriendBuffInfo(cfg.DataStream fs) {
		this.likability = fs.getFloat();
		this.buffname = fs.getString();
		this.introduction = fs.getString();
		this.buffid = fs.getInt();
	}
}
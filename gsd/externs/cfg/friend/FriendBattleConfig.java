package cfg.friend;
public final class FriendBattleConfig extends cfg.CfgObject {
	public final static int TYPEID = -569244610;
	final public int getTypeId() { return TYPEID; }
	public final float likabilitybykill;
	public final float likabilitybyectype;
	public final float buffinterval;
	public final java.util.List<cfg.friend.FriendBuffInfo> buffinfo = new java.util.ArrayList<>();
	public FriendBattleConfig(cfg.DataStream fs) {
		this.likabilitybykill = fs.getFloat();
		this.likabilitybyectype = fs.getFloat();
		this.buffinterval = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.buffinfo.add(new cfg.friend.FriendBuffInfo(fs));
		}
	}
}
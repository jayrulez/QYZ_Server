package cfg.tips;
public final class GiftBagBroadcast extends cfg.CfgObject {
	public final static int TYPEID = 1303878901;
	final public int getTypeId() { return TYPEID; }
	public final int giftbagid;
	public final java.util.Set<Integer> needbroadcastitemids = new java.util.HashSet<>();
	public GiftBagBroadcast(cfg.DataStream fs) {
		this.giftbagid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.needbroadcastitemids.add(fs.getInt());
		}
	}
}
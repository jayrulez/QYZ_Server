package cfg.family;
public final class BlackMarketOpenTime extends cfg.CfgObject {
	public final static int TYPEID = -2034558830;
	final public int getTypeId() { return TYPEID; }
	public final int starthour;
	public final int startminute;
	public final int endhour;
	public final int endminute;
	public BlackMarketOpenTime(cfg.DataStream fs) {
		this.starthour = fs.getInt();
		this.startminute = fs.getInt();
		this.endhour = fs.getInt();
		this.endminute = fs.getInt();
	}
}
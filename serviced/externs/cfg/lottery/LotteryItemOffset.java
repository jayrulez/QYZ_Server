package cfg.lottery;
public final class LotteryItemOffset extends cfg.CfgObject {
	public final static int TYPEID = 839758112;
	final public int getTypeId() { return TYPEID; }
	public final int itemid;
	public final float x;
	public final float y;
	public final float z;
	public final float scale;
	public LotteryItemOffset(cfg.DataStream fs) {
		this.itemid = fs.getInt();
		this.x = fs.getFloat();
		this.y = fs.getFloat();
		this.z = fs.getFloat();
		this.scale = fs.getFloat();
	}
}
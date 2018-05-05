package cfg.lottery;
public final class LotteryTextureLevel extends cfg.CfgObject {
	public final static int TYPEID = -1840181181;
	final public int getTypeId() { return TYPEID; }
	public final String pickcardtimes;
	public final java.util.Map<Integer, cfg.lottery.LotteryTextureDetail> texturedetail = new java.util.HashMap<>();
	public LotteryTextureLevel(cfg.DataStream fs) {
		this.pickcardtimes = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.texturedetail.put(_k, new cfg.lottery.LotteryTextureDetail(fs));
		}
	}
}
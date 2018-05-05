package cfg.lottery;
public final class LotteryTexture extends cfg.CfgObject {
	public final static int TYPEID = -1307849791;
	final public int getTypeId() { return TYPEID; }
	public final int recievedcurrency;
	public final String name;
	public final int order;
	public final String recievedwholecardbgicon;
	public final String exchangeicon;
	public final java.util.Map<Integer, cfg.lottery.LotteryTextureLevel> levels = new java.util.HashMap<>();
	public LotteryTexture(cfg.DataStream fs) {
		this.recievedcurrency = fs.getInt();
		this.name = fs.getString();
		this.order = fs.getInt();
		this.recievedwholecardbgicon = fs.getString();
		this.exchangeicon = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.levels.put(_k, new cfg.lottery.LotteryTextureLevel(fs));
		}
	}
}
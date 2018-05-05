package cfg.mall;
public final class BlackMall extends cfg.mall.Mall {
	public final static int TYPEID = 1438787075;
	final public int getTypeId() { return TYPEID; }
	public final int page;
	public final float openprobability;
	public BlackMall(cfg.DataStream fs) {
		super(fs);
		this.page = fs.getInt();
		this.openprobability = fs.getFloat();
	}
}
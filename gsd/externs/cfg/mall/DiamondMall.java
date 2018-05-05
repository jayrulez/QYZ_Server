package cfg.mall;
public final class DiamondMall extends cfg.mall.Mall {
	public final static int TYPEID = 1435398360;
	final public int getTypeId() { return TYPEID; }
	public final int page;
	public DiamondMall(cfg.DataStream fs) {
		super(fs);
		this.page = fs.getInt();
	}
}
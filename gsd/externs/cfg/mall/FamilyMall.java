package cfg.mall;
public final class FamilyMall extends cfg.mall.Mall {
	public final static int TYPEID = -188564920;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.MinFamilyShopLevel minfamilylevel;
	public FamilyMall(cfg.DataStream fs) {
		super(fs);
		this.minfamilylevel = new cfg.cmd.condition.MinFamilyShopLevel(fs);
	}
}
package cfg.cmd.condition;
public final class MinFamilyShopLevel extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 665993929;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public MinFamilyShopLevel(cfg.DataStream fs) {
		super(fs);
		this.level = fs.getInt();
	}
}
package cfg.mall;
public final class PocketShop extends cfg.mall.Mall {
	public final static int TYPEID = -366321572;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.MinLevel requirelevel;
	public PocketShop(cfg.DataStream fs) {
		super(fs);
		this.requirelevel = new cfg.cmd.condition.MinLevel(fs);
	}
}
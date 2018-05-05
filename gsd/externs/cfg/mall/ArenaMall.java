package cfg.mall;
public final class ArenaMall extends cfg.mall.Mall {
	public final static int TYPEID = 378934539;
	final public int getTypeId() { return TYPEID; }
	public final int page;
	public ArenaMall(cfg.DataStream fs) {
		super(fs);
		this.page = fs.getInt();
	}
}
package cfg.mall;
public final class TeamFightScore extends cfg.mall.Mall {
	public final static int TYPEID = -1800790705;
	final public int getTypeId() { return TYPEID; }
	public final int page;
	public TeamFightScore(cfg.DataStream fs) {
		super(fs);
		this.page = fs.getInt();
	}
}
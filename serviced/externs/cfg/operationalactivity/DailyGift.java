package cfg.operationalactivity;
public final class DailyGift extends cfg.operationalactivity.ActivityCondition {
	public final static int TYPEID = -968341006;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.MinVipLevel viplimit;
	public final cfg.cmd.condition.DayLimit limit;
	public final cfg.cmd.condition.Limit totallimit;
	public final cfg.cmd.condition.YuanBao original;
	public final cfg.cmd.condition.YuanBao off;
	public DailyGift(cfg.DataStream fs) {
		super(fs);
		this.viplimit = new cfg.cmd.condition.MinVipLevel(fs);
		this.limit = new cfg.cmd.condition.DayLimit(fs);
		this.totallimit = new cfg.cmd.condition.Limit(fs);
		this.original = new cfg.cmd.condition.YuanBao(fs);
		this.off = new cfg.cmd.condition.YuanBao(fs);
	}
}
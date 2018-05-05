package cfg.shakemoneytree;
public final class ShakeDetail extends cfg.CfgObject {
	public final static int TYPEID = 613237195;
	final public int getTypeId() { return TYPEID; }
	public final int shaketimes;
	public final cfg.cmd.condition.YuanBao cost;
	public final cfg.cmd.action.XuNiBi getmoney;
	public final float criticalrate;
	public final float criticalvalue;
	public final cfg.cmd.condition.MinVipLevel limit;
	public ShakeDetail(cfg.DataStream fs) {
		this.shaketimes = fs.getInt();
		this.cost = new cfg.cmd.condition.YuanBao(fs);
		this.getmoney = new cfg.cmd.action.XuNiBi(fs);
		this.criticalrate = fs.getFloat();
		this.criticalvalue = fs.getFloat();
		this.limit = new cfg.cmd.condition.MinVipLevel(fs);
	}
}
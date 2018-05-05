package cfg.family;
public final class BossFeed extends cfg.CfgObject {
	public final static int TYPEID = 1572911243;
	final public int getTypeId() { return TYPEID; }
	public final int feedid;
	public final cfg.cmd.condition.VipLimits feedlimit;
	public final int exp;
	public final cfg.cmd.action.AddFamilyMoneyBuild buildrate;
	public final cfg.cmd.action.BangGong familycontribution;
	public BossFeed(cfg.DataStream fs) {
		this.feedid = fs.getInt();
		this.feedlimit = new cfg.cmd.condition.VipLimits(fs);
		this.exp = fs.getInt();
		this.buildrate = new cfg.cmd.action.AddFamilyMoneyBuild(fs);
		this.familycontribution = new cfg.cmd.action.BangGong(fs);
	}
}
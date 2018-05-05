package cfg.huiwu;
public final class rankAward extends cfg.CfgObject {
	public final static int TYPEID = -1003032589;
	final public int getTypeId() { return TYPEID; }
	public final int rank;
	public final cfg.cmd.action.MultiBonus award;
	public rankAward(cfg.DataStream fs) {
		this.rank = fs.getInt();
		this.award = new cfg.cmd.action.MultiBonus(fs);
	}
}
package cfg.family;
public final class FamilyTaskReward extends cfg.CfgObject {
	public final static int TYPEID = 2001503608;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final long exp;
	public final long gold;
	public final long familyexp;
	public final long familygold;
	public FamilyTaskReward(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.exp = fs.getLong();
		this.gold = fs.getLong();
		this.familyexp = fs.getLong();
		this.familygold = fs.getLong();
	}
}
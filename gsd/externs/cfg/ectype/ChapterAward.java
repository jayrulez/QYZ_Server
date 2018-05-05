package cfg.ectype;
public final class ChapterAward extends cfg.CfgObject {
	public final static int TYPEID = 1007330044;
	final public int getTypeId() { return TYPEID; }
	public final int awardid;
	public final int requirestar;
	public final cfg.cmd.action.MultiBonus award;
	public ChapterAward(cfg.DataStream fs) {
		this.awardid = fs.getInt();
		this.requirestar = fs.getInt();
		this.award = new cfg.cmd.action.MultiBonus(fs);
	}
}
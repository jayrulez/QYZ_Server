package cfg.bonus;
public final class SpringBonusDetail extends cfg.CfgObject {
	public final static int TYPEID = -456968886;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String desc;
	public final cfg.common.DateTime starttime;
	public final cfg.cmd.action.MultiBonus bonus;
	public SpringBonusDetail(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.desc = fs.getString();
		this.starttime = new cfg.common.DateTime(fs);
		this.bonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
package cfg.friend;
public final class BonusList extends cfg.CfgObject {
	public final static int TYPEID = -429971913;
	final public int getTypeId() { return TYPEID; }
	public final int bonusid;
	public final int frienddegree;
	public final String introduction;
	public final cfg.cmd.action.Items bonus;
	public BonusList(cfg.DataStream fs) {
		this.bonusid = fs.getInt();
		this.frienddegree = fs.getInt();
		this.introduction = fs.getString();
		this.bonus = new cfg.cmd.action.Items(fs);
	}
}
package cfg.bonus;
public final class RankBonusList extends cfg.CfgObject {
	public final static int TYPEID = -667535784;
	final public int getTypeId() { return TYPEID; }
	public final int requirerank;
	public final cfg.cmd.action.Bonus bonuslist;
	public RankBonusList(cfg.DataStream fs) {
		this.requirerank = fs.getInt();
		this.bonuslist = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}
package cfg.bonus;
public final class RankBonus extends cfg.CfgObject {
	public final static int TYPEID = 1153363610;
	final public int getTypeId() { return TYPEID; }
	public final int ranktype;
	public final java.util.List<cfg.bonus.RankBonusList> bonuslist = new java.util.ArrayList<>();
	public RankBonus(cfg.DataStream fs) {
		this.ranktype = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bonuslist.add(new cfg.bonus.RankBonusList(fs));
		}
	}
}
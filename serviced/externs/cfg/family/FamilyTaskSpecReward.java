package cfg.family;
public final class FamilyTaskSpecReward extends cfg.CfgObject {
	public final static int TYPEID = -2097601389;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final int exp;
	public final int gold;
	public final cfg.cmd.action.MultiBonus exbonus;
	public final java.util.List<cfg.family.SpecBonus> specbonuslist = new java.util.ArrayList<>();
	public FamilyTaskSpecReward(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.exp = fs.getInt();
		this.gold = fs.getInt();
		this.exbonus = new cfg.cmd.action.MultiBonus(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.specbonuslist.add(new cfg.family.SpecBonus(fs));
		}
	}
}
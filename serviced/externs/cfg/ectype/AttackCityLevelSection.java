package cfg.ectype;
public final class AttackCityLevelSection extends cfg.CfgObject {
	public final static int TYPEID = -1838040032;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.MaxLevel requirelevel;
	public final int groupnum;
	public final int mainregionid;
	public final java.util.List<Integer> bornregion = new java.util.ArrayList<>();
	public final cfg.cmd.action.OneItems bonusids;
	public final java.util.List<cfg.ectype.AttackCityRefreshMonster> monsterinfos = new java.util.ArrayList<>();
	public AttackCityLevelSection(cfg.DataStream fs) {
		this.requirelevel = new cfg.cmd.condition.MaxLevel(fs);
		this.groupnum = fs.getInt();
		this.mainregionid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bornregion.add(fs.getInt());
		}
		this.bonusids = new cfg.cmd.action.OneItems(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterinfos.add(new cfg.ectype.AttackCityRefreshMonster(fs));
		}
	}
}
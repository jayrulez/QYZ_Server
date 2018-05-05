package cfg.ectype;
public final class HeroEctype extends cfg.CfgObject {
	public final static int TYPEID = -943805090;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int bossid;
	public final float uiscale;
	public final int mainregionid;
	public final int monsterreftime;
	public final java.util.List<cfg.ectype.MonsterInfo> monsters = new java.util.ArrayList<>();
	public final java.util.List<Integer> showbonusid = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.HeroEctypeBonus> heroectypebonus = new java.util.ArrayList<>();
	public HeroEctype(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.bossid = fs.getInt();
		this.uiscale = fs.getFloat();
		this.mainregionid = fs.getInt();
		this.monsterreftime = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsters.add(new cfg.ectype.MonsterInfo(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.showbonusid.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.heroectypebonus.add(new cfg.ectype.HeroEctypeBonus(fs));
		}
	}
}
package cfg.ectype;
public final class ExpMonsterMsg extends cfg.CfgObject {
	public final static int TYPEID = 1028749272;
	final public int getTypeId() { return TYPEID; }
	public final int minlevel;
	public final int maxlevel;
	public final int battlepower;
	public final int monsterid;
	public final float positionx;
	public final float positiony;
	public final float scale;
	public final java.util.List<cfg.ectype.ExpMonsterRef> monsterref = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.ExpMonsterBonus> monsterbonus = new java.util.ArrayList<>();
	public ExpMonsterMsg(cfg.DataStream fs) {
		this.minlevel = fs.getInt();
		this.maxlevel = fs.getInt();
		this.battlepower = fs.getInt();
		this.monsterid = fs.getInt();
		this.positionx = fs.getFloat();
		this.positiony = fs.getFloat();
		this.scale = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterref.add(new cfg.ectype.ExpMonsterRef(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterbonus.add(new cfg.ectype.ExpMonsterBonus(fs));
		}
	}
}
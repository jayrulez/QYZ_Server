package cfg.ectype;
public final class FloorInfo extends cfg.CfgObject {
	public final static int TYPEID = 405870;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int regionid;
	public final int score;
	public final int requirelevel;
	public final cfg.cmd.action.MultiBonus firstbonus;
	public final cfg.cmd.action.MultiBonus normalbonus;
	public final java.util.Map<Integer, Integer> monsters = new java.util.HashMap<>();
	public final int battlevalue;
	public final int basehp;
	public final int basedefence;
	public FloorInfo(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.regionid = fs.getInt();
		this.score = fs.getInt();
		this.requirelevel = fs.getInt();
		this.firstbonus = new cfg.cmd.action.MultiBonus(fs);
		this.normalbonus = new cfg.cmd.action.MultiBonus(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.monsters.put(_k, fs.getInt());
		}
		this.battlevalue = fs.getInt();
		this.basehp = fs.getInt();
		this.basedefence = fs.getInt();
	}
}